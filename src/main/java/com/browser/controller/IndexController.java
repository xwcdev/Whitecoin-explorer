package com.browser.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.browser.config.RealData;
import com.browser.dao.entity.*;
import com.browser.service.impl.AddressBalanceServiceImpl;
import com.browser.service.impl.RedisService;
import com.browser.task.vo.PriceInfo;
import com.browser.tools.common.DateUtil;
import com.google.gson.JsonObject;
import kong.unirest.Unirest;
import net.sf.json.util.JSONUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.browser.service.StatisService;
import com.browser.tools.common.StringUtil;
import com.browser.tools.controller.BaseController;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private StatisService statisService;
	@Autowired
    private AddressBalanceServiceImpl addressBalanceService;
	@Autowired
	private RedisService redisService;

	@Autowired
	private RealData realData;

	private static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping("")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping("mainCoinPrice")
	public ResultMsg mainCoinPrice() {
		ResultMsg resultMsg = new ResultMsg();
		JSONObject coinPriceInfo = new JSONObject();


		try {

			String urlNameStringUsdt = "https://api.xt.pub/data/api/v1/getTicker?market=xwc_usdt";
			String urlNameStringBtc = "https://api.xt.pub/data/api/v1/getTicker?market=xwc_btc";


			String usdtResult="";
			String btcResult="";

			// 根据地址获取请求
			HttpGet requestUsdt = new HttpGet(urlNameStringUsdt);
			requestUsdt.setHeader("Content-Type","application/x-www-form-urlencoded");
			// 获取当前客户端对象
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(requestUsdt);
			logger.info("调用聚合行情接口返回:{}",response);

			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				usdtResult= EntityUtils.toString(response.getEntity(),"utf-8");
				usdtResult = Unirest.get(urlNameStringUsdt).asString().getBody();
				logger.info("调用聚合行情接口返回str: {}", usdtResult);
				JSONObject jsStr = JSONObject.parseObject(usdtResult);
				BigDecimal price = new BigDecimal(jsStr.get("price").toString());
				BigDecimal rate = new BigDecimal(jsStr.get("rate").toString());
				BigDecimal low = new BigDecimal(jsStr.get("low").toString());
				BigDecimal high = new BigDecimal(jsStr.get("high").toString());
				PriceInfo mainCoinUsdtPriceInfo = new PriceInfo();
				mainCoinUsdtPriceInfo.setChange(rate);
				mainCoinUsdtPriceInfo.setPrice(price);
				mainCoinUsdtPriceInfo.setLow(low);
				mainCoinUsdtPriceInfo.setHigh(high);
				coinPriceInfo.put("in_usdt", mainCoinUsdtPriceInfo);
			}


			// 根据地址获取请求
			HttpGet requestBtc = new HttpGet(urlNameStringBtc);
			requestBtc.setHeader("Content-Type","application/x-www-form-urlencoded");

			// 通过请求对象获取响应对象
			HttpResponse responseBtc = httpClient.execute(requestBtc);
			logger.info("调用聚合行情接口返回:{}",responseBtc);

			// 判断网络连接状态码是否正常(0--200都数正常)
			if (responseBtc.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				btcResult= EntityUtils.toString(responseBtc.getEntity(),"utf-8");
				btcResult = Unirest.get(urlNameStringBtc).asString().getBody();
				logger.info("调用聚合行情接口返回str: {}", btcResult);
				JSONObject jsStr = JSONObject.parseObject(btcResult);
				BigDecimal price = new BigDecimal(jsStr.get("price").toString());
				BigDecimal rate = new BigDecimal(jsStr.get("rate").toString());
				BigDecimal low = new BigDecimal(jsStr.get("low").toString());
				BigDecimal high = new BigDecimal(jsStr.get("high").toString());
				PriceInfo mainCoinBtcPriceInfo = new PriceInfo();
				mainCoinBtcPriceInfo.setChange(rate);
				mainCoinBtcPriceInfo.setPrice(price);
				mainCoinBtcPriceInfo.setLow(low);
				mainCoinBtcPriceInfo.setHigh(high);
				coinPriceInfo.put("in_btc", mainCoinBtcPriceInfo);
			}



			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(coinPriceInfo);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
		return resultMsg;
	}

	/**
	 * 获取最新统计信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getStatis", method = { RequestMethod.POST })
	public ResultMsg getStatis() {
		ResultMsg resultMsg = new ResultMsg();
		try {
			BlStatis data = realData.getBlStatis();
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
    @RequestMapping(value = "richlist", method = RequestMethod.GET)
    public ResultMsg richList() {
        ResultMsg resultMsg = new ResultMsg();
        try {
            List<BLAddressBalance> topBalances = addressBalanceService.selectTopTichList("1.3.0", 100);
            resultMsg.setRetCode(ResultMsg.HTTP_OK);
            resultMsg.setData(topBalances);
        } catch (Exception e) {
            logger.error("系统错误", e);
            resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
            resultMsg.setRetMsg(e.getMessage());
        }
        return resultMsg;
    }

	/**
	 * 获取最新区块信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "blocksInfo", method = { RequestMethod.POST })
	public ResultMsg blocksInfo() {
		ResultMsg resultMsg = new ResultMsg();
		try {
			List<BlBlock> data = statisService.newBlockStatic();
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
		return resultMsg;
	}

	/**
	 * 获取最新交易信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getTrxance", method = { RequestMethod.POST })
	public ResultMsg getTrxance(HttpServletRequest request) {
		ResultMsg resultMsg = new ResultMsg();
		try {
			List<BlTransaction> data = statisService.newTransactionStatic();
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
		return resultMsg;
	}

	/**
	 * 获取每天图表最新交易量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getDayTrxNum", method = { RequestMethod.POST })
	public ResultMsg getTrxNum(@RequestBody BlTransaction transaction) {
		ResultMsg resultMsg = new ResultMsg();
		if (StringUtil.isEmpty(transaction.getEndTime()) || StringUtil.isEmpty(transaction.getStartTime())) {
			resultMsg.setRetCode(ResultMsg.HTTP_CHECK_VALID);
			resultMsg.setRetMsg("查询参数不允许为空！");
			return resultMsg;
		}
		try {
			long day = DateUtil.getDaySub(transaction.getStartTime(),transaction.getEndTime());
			JSONArray data =new JSONArray();
			if(day>15){
				data = realData.getMonthDay();
			}else{
				data = realData.getWeekDay();
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
	
	/**
	 * 获取每小时图表最新交易量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getHourTrxNum", method = { RequestMethod.POST })
	public ResultMsg getHourTrxNum(@RequestBody BlTransaction transaction) {
		ResultMsg resultMsg = new ResultMsg();
		if (StringUtil.isEmpty(transaction.getEndTime()) || StringUtil.isEmpty(transaction.getStartTime())) {
			resultMsg.setRetCode(ResultMsg.HTTP_CHECK_VALID);
			resultMsg.setRetMsg("查询参数不允许为空！");
			return resultMsg;
		}
		try {
			JSONArray data = realData.getToday();
			resultMsg.setRetCode(ResultMsg.HTTP_OK);
			resultMsg.setData(data);
		} catch (Exception e) {
			logger.error("系统错误", e);
			resultMsg.setRetCode(ResultMsg.HTTP_ERROR);
			resultMsg.setRetMsg(e.getMessage());
		}
		return resultMsg;
	}

	public static void main(String[] args) {


		String urlNameString = "https://api.xt.pub/data/api/v1/getTicker?market=xwc_btc";


		String result="";
		try {
			// 根据地址获取请求
			HttpGet request = new HttpGet(urlNameString);
			request.setHeader("Content-Type","application/x-www-form-urlencoded");
			// 获取当前客户端对象
			HttpClient httpClient = new DefaultHttpClient();
			// 通过请求对象获取响应对象
			HttpResponse response = httpClient.execute(request);

			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result= EntityUtils.toString(response.getEntity(),"utf-8");

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		Object jsStr = (Object)JSONObject.parseObject(result);
		/*String price = jsStr.get("price").toString();
		String rate = jsStr.get("rate").toString();*/
		PriceInfo priceInfo = (PriceInfo)jsStr;
		System.out.println(priceInfo);
		//System.out.println(rate);

	}
}
