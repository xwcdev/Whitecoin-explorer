<template>
  <div class="wrap">
    <div class="tr_main">
      <div class="con_top">
        <p>{{ $t("miner.title") }}</p>
        <Search class="search_con" />
      </div>
      <div class="con_all">
        <div class="table-wrap">
          <el-table :data="dataList" style="width: 100%">
            <el-table-column align="center" :label="$t('miner.name')">
              <template slot-scope="scope">
                {{ scope.row.minerName }}
                {{
                  scope.row.lvl === 1
                    ? "(" + $t("miner.officialMiner") + ")"
                    : ""
                }}
              </template>
            </el-table-column>
            <el-table-column
              align="center"
              :label="$t('miner.weights')"
              prop="pledgeWeight"
            ></el-table-column>
            <el-table-column
              align="center"
              prop="totalProduced"
              :label="$t('miner.numberBlocks')"
              width="120"
            >
            </el-table-column>
            <el-table-column
              align="center"
              prop="totalMissed"
              :label="$t('miner.missed')"
            >
            </el-table-column>
            <el-table-column
              align="center"
              :label="$t('miner.lastTime')"
              prop="lastConfirmedBlockNum"
              width="150"
            ></el-table-column>
            <el-table-column align="center" :label="$t('miner.fee')">
              <template slot-scope="scope">
                {{ scope.row.minerPledgePayBack + "%" }}
              </template>
            </el-table-column>
          </el-table>
        </div>
        <el-pagination
          class="pagination"
          layout="prev, pager, next, jumper"
          :current-page="page"
          :page-size="size"
          :total="total"
          @current-change="pageChange"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import bus from "../utils/bus";
import Search from "../components/search/Search";
export default {
  name: "miner",
  components: { Search },
  data() {
    return {
      page: 1,
      size: 100,
      total: 0,
      dataList: []
    };
  },
  mounted() {
    this.getdataListData();

    bus.navChoice = 4;
  },
  methods: {
    getdataListData() {
      let that = this;
      this.$axios
        .post("/getMinerList", { page: this.page, rows: this.size })
        .then(function(res) {
          if (res.data.retCode === 200 && res.data.retCode === 200) {
            let data = res.data.data;
            that.dataList = data.rows;
            that.total = data.total;
          }
        });
    },
    pageChange(page) {
      this.page = page;
      this.getdataListData();
    }
  }
};
</script>

<style lang="less" scoped>
.wrap {
  .tr_main {
    width: 1140px;
    margin: 0 auto;
    position: relative;
    color: black;
    .con_top {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-top: 30px;
      p {
        font-size: 22px;
        color: #333333;
        font-weight: 600;
      }
    }
    .con_all {
      background: #fff;
      box-shadow: 0px 2px 13px 0px rgba(0, 0, 0, 0.09);
      margin: 30px 0;
      padding: 30px;
      border-radius: 5px;
      a {
        color: #0279ff;
        &:hover {
          color: #333;
        }
      }
    }
    .pagination {
      text-align: center;
      margin-top: 20px;
    }
  }
}
</style>
