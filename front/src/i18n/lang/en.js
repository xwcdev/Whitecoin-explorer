import enLocale from 'element-ui/lib/locale/lang/en'
const en = {
  nav: {
    home: 'Home',
    blocks: 'Blocks',
    transactions: 'Transactions',
    contracts: 'Contracts',
    richlist: 'Richlist',
    tokens: 'Tokens',
    ziyuan: 'Resource',
    assets: 'Assets'
  },
  maxTitle:{
    t1:'All Transactions',
    t2:'Tokens Transactions',
  },
  alert: {
    noSpace:'No space can be available',
    enterContent:'Please enter the search content',
    notSearch:'Not searching for relevant content'
  },
  search: {
    placeholder: 'Address/TxHash/Contract/Height/Account Name',
    placeholder2:'Please enter your token name/contract address',
  },
  home: {
    blockchinaInfo: {
      title: 'Blockchain Info',
      totalSupply: 'Circulating Supply',
      transactions: 'Transactions',
      blockHeight: 'Block Height',
      blockReward: 'Block Reward',
      crossAsset: 'Cross Assets',
      accountCount: 'Total Supply'
    },
    valueInfo: {
      title: 'Value Info',
      price: 'Price',
      priceBTC: 'Price(BTC)',
      change: 'Change(24H)'
    },
    blocks: {
      title: 'Blocks',
      more: 'More',
      height: 'Height',
      age: 'Age',
      transactions: 'Transactions',
      miner: 'Miner',
      size: 'Size',
      reward: 'Reward',
      minerFee:'Gas Fee',
      over:'finished',
      success:'ordes',
    },
    transaction: {
      title: 'Transaction',
      more: 'More',
      txHash: 'TxHash',
      block: 'Block',
      types: 'Types',
      age: 'Age',
      value: 'Value',
      fee: 'Fee',
      numberss:'Amount',
      fromDeafult:'Create Contract'
    },
    transactionLine: {
      today: 'Today',
      week: 'Week',
      month: 'Month',
      summary: 'Transaction Statistics'
    },
    footer: {
      t1: 'Whitepaper',
      t2: 'Contact',
      t3: 'Event',
      t4: 'Support',
      t5: 'Links',
    },
  },
  transferDetails: {
    tips: {
      overview: 'Overview',
      event:'Event'
    },
    information: {
      title: 'Transaction Information',
      txHash: 'TxHash:',
      status: 'TxReceipt Status:',
      type: 'TxReceipt Type:',
      height: 'Block Height:',
      timeStamp: 'TimeStamp:'
    },
    tokenTransfers: {
      title: 'Token Transfers',
      transfer: 'Transfer',
    },
    contractBalanceChangeDetail: {
      title: 'Contract Balance Changes',
      contract_withdraw: 'Withdraw from contract',
      deposit_contract: 'Deposit to contract',
      deposit_to_address: 'Address Received from contract',
    },
    swapEvent: {
      title: 'Swap Event',
      NativeBalanceChange: 'Native Balance Change',
      Exchanged: 'Exchange',
      LiquidityTokenMinted: 'Minted Liquidity Token',
      LiquidityTokenDestoryed: 'Destroyed Liquidity Token',
      LiquidityAdded: 'Added Liquidity',
      LiquidityRemoved: 'Removed Liquidity'
    },
    details: {
      title: 'Details',
      transfer: 'Transfer',
      txHash: 'TxHash:',
      value: 'Value:',
      from: 'From:',
      memo: 'Memo:',
      address: 'Contract Address:',
      gasLimit: 'Gas Limit:',
      gasPrice:'Gas Price:',
      inoutData: 'Input Data:',
      contractInvoke: 'Contract Invoke',
      contractAddress:'Contract Address:',
      callerAddress:'Caller Address:',
      actualTxCost_Fee:'Actual Tx Cost/Fee:',
      acceptance:'Acceptance',
      id:'ID:',
      exchange:'Exchange:',
      amount:'Amount:',
      contractRegister: 'Contract Register',
      authorAddress:'Author Address:',
      inheritFrom:'Inherit From:',
      rate:'Rate:',
      contractTransfer:'Contract Transfer',
      contractUpgrade:'Contract Upgrade',
      contractName:'Contract Name',
      description:'Description:',
      crosschainRecord:'Crosschain Record',
      minerName:'Miner Name:',
      minerAddress:'Miner Address:',
      fee:'Fee:',
      to:'To:',
      crosschainWithdraw:'Crosschain Withdraw',
      crosschainWithdrawResult:'Crosschain Withdraw Result',
      accountBind:'Account Bind',
      asset:'Asset:',
      tunnelAddress:'Tunnel Address:',
      guardRefundCrosschainTrx:'Guard Refund Crosschain Trx',
      guardAddress:'Guard Address:',
      guardID:'Guard ID:',
      cancelledTxHash:'Cancelled TxHash:',
      accountCreate:'Account Create',
      name:'Name',
      minerCreate:'Miner Create',
      minerGenerateMultiAsset:'Miner Generate Multi Asset',
      hotAddress:'Hot Address:',
      coldAddress:'Cold Address:',
      payBack:'Pay Back',
      ownerAddress:'Owner Address:',
      acceptanceCreate:'Acceptance Create',
      valueTarget:'Value(Target):',
      acceptanceCancel:'Acceptance Cancel',
      acceptanceID:'Acceptance ID',
      toAddress:'ToAddress',
      fromAddress:'FromAddress'
    }
  },
  miner: {
    overview: {
      title: 'Miner Overview',
      name: 'Name:',
      address: 'Address:',
      contracts: 'Contracts:',
      transaction: 'Transaction:',
      rewards: 'Rewards:'
    },
    myTransactions: {
      title: 'Transfer',
      txHash: 'TxHash',
      block: 'Block',
      types: 'Types',
      age: 'Age',
      from: 'From',
      to: 'To',
      value: 'Value',
      fee: 'Fee'
    },
    myContracts: {
      title: 'My Contracts',
      address: 'Contract Address',
      types: 'Types',
      call: 'Call Times',
      create: 'Create Times',
      last: 'The Last Time'
    }
  },
  contracts: {
    title: 'Contracts',
    contractAddress: 'Contract Address',
    types: 'Types',
    authorAddress: 'Author Address',
    callTimes: 'CallTimes',
    createTime: 'Create Time',
    lastTime: 'The Last Time',
    total_span_before: 'A Total Of',
    total_span_after: 'verified contract source codes found'
  },
  tokens: {
    title: 'Tokens',
    contractAddress: 'Contract Address',
    types: 'Types',
    authorAddress: 'Author Address',
    callTimes: 'CallTimes',
    createTime: 'Create Time',
    lastTime: 'The Last Time',
    total_span_before: 'A Total Of',
    total_span_after: 'tokens found',
    tokenSymbol: 'Symbol',
    precision: 'Precision',
    tokenSupply: 'Supply',
    tokenAddressNum: 'Number of addresses'
  },
  richlist: {
    title: 'Rich List',
    address: 'Address',
    accountName: 'Account Name',
    amount: 'Amount',
    teamHold: 'Hold by the Team',
    oldExchange: 'For Swapping',
  },
  contractOverview: {
    title: 'Contract Overview',
    contractAddress: 'Contract Address:',
    authorAddress: 'Author Address:',
    transaction: 'Transaction:',
    balance: 'Balance',
    createTxn: 'Create txn:',
    tableTitle: 'Transactions',
    api:'Api',
    txHash: 'TxHash',
    block: 'Block',
    time: 'Time',
    callerAddress: 'Caller Address',
    value: 'Value',
    fee: 'Fee'
  },
  blocks: {
    title: 'Blocks',
    height: 'Height',
    age: 'Age',
    txn: 'txn',
    miner: 'Miner',
    size: 'Size',
    reward: 'Reward',
    total_span_before: 'A Total Of',
    total_span_after: 'blocks found',
    overview: {
      name: 'Overview',
      title: 'Block Information',
      hash: 'Hash:',
      height: 'Height:',
      timeStamp: "TimeStamp:",
      transactions: 'Transactions:',
      miner: 'Miner:',
      size: 'Size:',
      rewards: 'Rewards:',
      version: 'Version:',
      merkleRoot: 'Merkle Root:'
    },
    transaction: {
      name: 'Transaction',
      txHash: 'TxHash',
      types: 'Types',
      value: 'Value',
      fee: 'Fee'
    }
  },
  transaction:{
    title:'Transaction',
    all:'ALL',
    transfer:'Transfer',
    withdraw:'Withdraw',
    recharge:'Deposit',
    contract:'Contract',
    wage:'Reward',
    acceptance:'Acceptance',
    foreclose: 'Foreclose',
    mortgage: 'Mortgage',
    other:'Other',
    txHash:'TxHash',
    block:'Block',
    types:'Types',
    age:'Age',
    from:'From',
    to:'To',
    value:'Value',
    fee:'Fee',
    address: 'Address',
    contractAddress:'Contract Address',
    authorAddress:'Author Address',
    createTime:'Create Time',
    exchange:'Exchange',
    balance:'Balance',
    callerTimes:'Caller Times',
    txs_found_before: '',
    txs_found_after: 'transactions found'
  },
  address: {
    overview: {
      title: 'Address',
      name: 'Name:',
      address: 'Address:',
      contracts: 'Contracts:',
      transaction: 'Transaction:',
      rewards: 'Rewards:',
      balances: 'Balances:',
      lockBalance:'Mortgaged assets:',
      paybackBalances: 'Payback:',
      abnormal_address: 'Abnormal Address',
      tokenBalances: 'Token Balances:',
    },
    myTransactions: {
      title: 'My Transactions',
      txHash: 'TxHash',
      block: 'Block',
      types: 'Types',
      age: 'Age',
      from: 'From',
      to: 'To',
      value: 'Value',
      fee: 'Fee'
    },
    myTokenTransactions: {
      title: 'My Token Transactions',
      txHash: 'TxHash',
      block: 'Block',
      types: 'Types',
      age: 'Age',
      from: 'From',
      to: 'To',
      value: 'Value',
      fee: 'Fee',
      symbol: 'Symbol'
    },
    mySwapTransactions: {
      address: 'Address',
      title: 'My Swap Transactions',
      txHash: 'TxHash',
      block: 'Block',
      types: 'Types',
      age: 'Age',
      from: 'From',
      to: 'To',
      value: 'Value',
      fee: 'Fee',
      symbol: 'Symbol'
    },
    myContracts: {
      title: 'My Contracts',
      address: 'Contract Address',
      types: 'Types',
      call: 'Call Times',
      create: 'Create Times',
      last: 'The Last Time'
    }
  },
  miner: {
    title: 'Miners',
    name: 'MINER',
    weights: 'Priority level',
    numberBlocks: 'Total blocks',
    missed: 'Missed blocks',
    lastTime: 'Blocks produced last time',
    fee: 'Rate',
    officialMiner: 'Official',
  },
  message:{
    success:'Copy successfully!',
    failed:'Copy failed!'
  },
  ...enLocale
}
export default en;
