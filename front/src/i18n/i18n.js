import Vue from 'vue'
import locale from 'element-ui/lib/locale';
import VueI18n from 'vue-i18n'
import messages from './lang'
Vue.use(VueI18n)
//从localStorage中拿到用户的语言选择，如果没有，那默认中文。
// const start = location.href.indexOf('?') + 1
// const query = location.href.substring(start)
// console.log(query ,'aaa');
// const lang={};
// query.split("&").forEach(item=>{
//    let obj = item.split("=");
//    lang[obj[0]] = obj[1]
// })
// console.log(lang)

const query=location.search.substr(1)
console.log(query);
const lang={};
query.split("&").forEach(item=>{
   let obj = item.split("=");
   lang[obj[0]] = obj[1]
})
console.log(lang)


const i18n = new VueI18n({
  locale: lang.lang || 'en',
  messages,
})
locale.i18n((key, value) => i18n.t(key, value)) //为了实现element插件的多语言切换

export default i18n
