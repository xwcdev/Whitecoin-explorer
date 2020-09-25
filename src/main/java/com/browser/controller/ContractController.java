package com.browser.controller;

import com.browser.dao.entity.*;
import com.browser.service.SwapContractService;
import com.browser.service.TokenService;
import com.browser.tools.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.browser.protocol.EUDataGridResult;
import com.browser.service.StatisService;
import com.browser.service.impl.ContractService;

/** 合约信息处理入口
 * Created by Administrator on 2017/12/8 0008.
 */
@Controller
public class ContractController {

    @Autowired
    private ContractService contractService;
    
    @Autowired
    private StatisService statisService;

    @Autowired
	private TokenService tokenService;
    @Autowired
	private SwapContractService swapContractService;

    @Value("${swap_contract.admin.password}")
	private String swapContractAdminPassword;
    
    private static Logger logger = LoggerFactory.getLogger(ContractController.class);

    @ResponseBody
	@GetMapping("add_swap_contract/{contractAddress}/{token1}/{token2}/{password}")
	public String addSwapContract(@PathVariable("contractAddress") String contractAddress,
								  @PathVariable("token1") String token1, @PathVariable("token2") String token2,
								  @PathVariable("password") String password) {
    	if(StringUtil.isEmpty(contractAddress)) {
    		return "invalid contract address";
		}
    	if(StringUtil.isEmpty(token1)) {
    		return "invalid token1";
		}
    	if(StringUtil.isEmpty(token2)) {
    		return "invalid token2";
		}
    	if(token1.equals(token2)) {
    		return "token1 should not be equal to token2";
		}
    	if(StringUtil.isEmpty(password)) {
    		return "empty password";
		}
    	if(!password.equals(swapContractAdminPassword)) {
    		return "invalid password";
		}
		contractAddress = contractAddress.trim();
    	token1 = token1.trim();
    	token2 = token2.trim();
    	BlSwapContract swapContract = swapContractService.selectByContractAddress(contractAddress);
    	if(swapContract != null) {
    		return "added before";
		}
    	swapContract = new BlSwapContract();
    	swapContract.setContractAddress(contractAddress);
    	swapContract.setToken1(token1);
    	swapContract.setToken2(token2);
    	swapContract.setVerified(true);
    	swapContractService.insert(swapContract);
    	return "added";
	}

    @ResponseBody
	@RequestMapping(value = "queryContractList", method = { RequestMethod.POST })
    public ResultMsg queryContractList(@RequestBody BlContractInfo contractInfo) {
    	ResultMsg resultMsg = new ResultMsg();
    	try {
			EUDataGridResult data = contractService.getContractList(contractInfo);
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
        return resultMsg;
    }

    @ResponseBody
	@RequestMapping(value = "getContractTrxList", method = { RequestMethod.POST })
    public ResultMsg getContractTrxList(@RequestBody BlTransaction transaction) {
    	ResultMsg resultMsg = new ResultMsg();
    	if(null==transaction.getContractId()) {
			resultMsg.setRetCode(ResultMsg.HTTP_CHECK_VALID);
			resultMsg.setRetMsg("参数不能为空");
			return resultMsg;
		}
    	try {
			EUDataGridResult data = contractService.getContractTrxList(transaction);
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
        return resultMsg;
    }
    
    @ResponseBody
    @RequestMapping(value = "getAbi", method = { RequestMethod.POST })
    public ResultMsg getAbi(@RequestBody BlContractInfo contractInfo) {
    	ResultMsg resultMsg = new ResultMsg();
    	if(null==contractInfo.getContractId()) {
			resultMsg.setRetCode(ResultMsg.HTTP_CHECK_VALID);
			resultMsg.setRetMsg("参数不能为空");
			return resultMsg;
		}
    	try {
			JSONObject data = contractService.getAbi(contractInfo);
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
    	return resultMsg;
    }
    
    @ResponseBody
    @RequestMapping(value = "getEvents", method = { RequestMethod.POST })
    public ResultMsg getEvents(@RequestBody BlContractInfo contractInfo) {
    	ResultMsg resultMsg = new ResultMsg();
    	if(null==contractInfo.getContractId()) {
			resultMsg.setRetCode(ResultMsg.HTTP_CHECK_VALID);
			resultMsg.setRetMsg("参数不能为空");
			return resultMsg;
		}
    	try {
			JSONObject data = contractService.getEvents(contractInfo);
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
    	return resultMsg;
    }
    
    @ResponseBody
    @RequestMapping(value = "getContractStatis", method = { RequestMethod.POST })
    public ResultMsg getContractTrxList(@RequestBody BlContractInfo contractInfo) {
    	ResultMsg resultMsg = new ResultMsg();
    	if(null==contractInfo.getContractId()) {
			resultMsg.setRetCode(ResultMsg.HTTP_CHECK_VALID);
			resultMsg.setRetMsg("参数不能为空");
			return resultMsg;
		}
    	try {
			BlContractDetail data = statisService.getContractsStatis(contractInfo);
			if(data != null) {
				BlToken token = tokenService.selectByContractAddress(contractInfo.getContractId());
				data.setTokenContract(token);
			}
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
    	return resultMsg;
    }

}
