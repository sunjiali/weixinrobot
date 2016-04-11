package org.liufeng.course.kuaidi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.liufeng.course.pojo.Express;

public class ExpressImp {
	
	public static void main(String[] args) {
		new ExpressImp().list();
	}
	
	public List<Express> list(){
		
		/**
		 * Express Map
		 */
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("aae", "AAE快递");
		map.put("ande", "安得物流");
		map.put("anjie", "安捷快递");
		map.put("anneng", "安能物流");
		map.put("anxun", "安迅物流");
		map.put("aoshuo", "奥硕物流");
		map.put("aramex", "Aramex国际快递");
		map.put("baiqian", "百千诚国际物流");
		map.put("balunzhi", "巴伦支");
		map.put("baotongda", "宝通达");
		map.put("benteng", "成都奔腾国际快递");
		map.put("changtong", "长通物流");
		map.put("chengguang", "程光快递");
		map.put("chengji", "城际快递");
		map.put("chengshi100", "城市100");
		map.put("chuanxi", "传喜快递");
		map.put("chuanzhi", "传志快递");
		map.put("chukouyi", "出口易物流");
		map.put("citylink", "CityLinkExpress");
		map.put("coe", "东方快递");
		map.put("coscon", "中国远洋运输(COSCON)");
		map.put("cszx", "城市之星");
		map.put("dada", "大达物流");
		map.put("dajin", "大金物流");
		map.put("dashun", "大顺物流");
		map.put("datian", "大田物流");
		map.put("dayang", "大洋物流快递");
		map.put("debang", "德邦物流");
		map.put("dhl", "DHL快递");
		map.put("diantong", "店通快递");
		map.put("disifang", "递四方速递");
		map.put("dpex", "DPEX快递");
		map.put("dsu", "D速快递");
		map.put("ees", "百福东方物流");
		map.put("ems", "EMS快递");
		map.put("eyoubao", "E邮宝");
		map.put("fangfangda", "方方达物流");
		map.put("fanyu", "凡宇快递");
		map.put("fardar", "Fardar");
		map.put("fedex", "国际Fedex");
		map.put("fedexcn", "Fedex国内");
		map.put("feibao", "飞豹快递");
		map.put("feihang", "原飞航物流");
		map.put("feite", "飞特物流");
		map.put("feiyang", "飞洋快递");
		map.put("feiyuan", "飞远物流");
		map.put("fengda", "丰达快递");
		map.put("gangkuai", "港快速递");
		map.put("gaotie", "高铁快递");
		map.put("gdyz", "广东邮政物流");
		map.put("gnxb", "邮政小包");
		map.put("gongsuda", "共速达物流|快递");
		map.put("guanda", "冠达快递");
		map.put("guotong", "国通快递");
		map.put("haihong", "山东海红快递");
		map.put("haolaiyun", "好来运快递");
		map.put("haosheng", "昊盛物流");
		map.put("hebeijianhua", "河北建华快递");
		map.put("henglu", "恒路物流");
		map.put("hengyu", "恒宇运通");
		map.put("hkpost", "香港邮政");
		map.put("huacheng", "华诚物流");
		map.put("huahan", "华翰物流");
		map.put("huahang", "华航快递");
		map.put("huangmajia", "黄马甲快递");
		map.put("huaqi", "华企快递");
		map.put("huayu", "华宇物流");
		map.put("huitong", "汇通快递");
		map.put("hutong", "户通物流");
		map.put("hwhq", "海外环球快递");
		map.put("intmail", "国际邮政快递");
		map.put("jiahuier", "佳惠尔快递");
		map.put("jiaji", "佳吉快运");
		map.put("jiayi", "佳怡物流");
		map.put("jiayu", "佳宇物流");
		map.put("jiayunmei", "加运美快递");
		map.put("jiete", "捷特快递");
		map.put("jinda", "金大物流");
		map.put("jingdong", "京东快递");
		map.put("jingguang", "京广快递");
		map.put("jingshi", "京世物流");
		map.put("jinyue", "晋越快递");
		map.put("jiuyi", "久易快递");
		map.put("jixianda", "急先达物流");
		map.put("jldt", "嘉里大通物流");
		map.put("jppost", "日本邮政");
		map.put("kcs", "顺鑫(KCS)快递");
		map.put("kuaijie", "快捷快递");
		map.put("kuaitao", "快淘速递");
		map.put("kuaiyouda", "快优达速递");
		map.put("kuanrong", "宽容物流");
		map.put("kuayue", "跨越快递");
		map.put("lanhu", "蓝弧快递");
		map.put("lejiedi", "乐捷递快递");
		map.put("lianhaotong", "联昊通快递");
		map.put("lijisong", "成都立即送快递");
		map.put("lindao", "上海林道货运");
		map.put("longbang", "龙邦快递");
		map.put("menduimen", "门对门快递");
		map.put("mengsu", "蒙速快递");
		map.put("minbang", "民邦快递");
		map.put("mingliang", "明亮物流");
		map.put("minsheng", "闽盛快递");
		map.put("nanbei", "南北快递");
		map.put("nell", "尼尔快递");
		map.put("nengda", "能达快递");
		map.put("nsf", "新顺丰（NSF）快递");
		map.put("ocs", "OCS快递");
		map.put("peixing", "陪行物流");
		map.put("pinganda", "平安达");
		map.put("pingyou", "中国邮政");
		map.put("ppbyb", "贝邮宝");
		map.put("quanchen", "全晨快递");
		map.put("quanfeng", "全峰快递");
		map.put("quanritong", "全日通快递");
		map.put("quanyi", "全一快递");
		map.put("ririshun", "日日顺物流");
		map.put("riyu", "日昱物流");
		map.put("rongqing", "荣庆物流");
		map.put("rpx", "RPX保时达");
		map.put("rufeng", "如风达快递");
		map.put("ruifeng", "瑞丰速递");
		map.put("saiaodi", "赛澳递");
		map.put("santai", "三态速递");
		map.put("scs", "伟邦(SCS)快递");
		map.put("shengan", "圣安物流");
		map.put("shengbang", "晟邦物流");
		map.put("shengfeng", "盛丰物流");
		map.put("shenghui", "盛辉物流");
		map.put("shentong", "申通快递");
		map.put("shiyun", "世运快递");
		map.put("shunfeng", "顺丰快递");
		map.put("simai", "思迈快递");
		map.put("singpost", "新加坡邮政");
		map.put("suchengzhaipei", "速呈宅配");
		map.put("suijia", "穗佳物流");
		map.put("sure", "速尔快递");
		map.put("suteng", "速腾快递");
		map.put("sutong", "速通物流");
		map.put("tiantian", "天天快递");
		map.put("tnt", "TNT快递");
		map.put("tongzhishu", "高考录取通知书");
		map.put("ucs", "合众速递");
		map.put("ups", "UPS快递");
		map.put("usps", "USPS快递");
		map.put("wanbo", "万博快递");
		map.put("wanjia", "万家物流");
		map.put("wanxiang", "万象物流");
		map.put("weitepai", "微特派");
		map.put("wuhuan", "五环速递");
		map.put("xianglong", "祥龙运通快递");
		map.put("xinbang", "新邦物流");
		map.put("xinfeng", "信丰快递");
		map.put("xingchengzhaipei", "星程宅配快递");
		map.put("xiyoute", "希优特快递");
		map.put("yad", "源安达快递");
		map.put("yafeng", "亚风快递");
		map.put("yanwen", "燕文物流");
		map.put("yibang", "一邦快递");
		map.put("yinjie", "顺捷丰达快递");
		map.put("yishunhang", "亿顺航快递");
		map.put("yousu", "优速快递");
		map.put("ytfh", "北京一统飞鸿快递");
		map.put("yuancheng", "远成物流");
		map.put("yuantong", "圆通快递");
		map.put("yuefeng", "越丰快递");
		map.put("yuhong", "宇宏物流");
		map.put("yumeijie", "誉美捷快递");
		map.put("yunda", "韵达快递");
		map.put("yuntong", "运通中港快递");
		map.put("zengyi", "增益快递");
		map.put("zhaijisong", "宅急送快递");
		map.put("zhengzhoujianhua", "郑州建华快递");
		map.put("zhima", "芝麻开门快递");
		map.put("zhongtian", "济南中天万运");
		map.put("zhongtie", "中铁快运");
		map.put("zhongtong", "中通快递");
		map.put("zhongxinda", "忠信达快递");
		map.put("zhongyou", "中邮物流");
		map.put("zongxing", "纵行物流");
		map.put("ztwl", "中铁物流");
		map.put("zuochuan", "佐川急便");
		List<Express> expList = new ArrayList<>();
		
		for(Map.Entry<String, String> entry: map.entrySet()){
			Express e = new Express();
			e.setExp_id(entry.getKey());
			e.setExpName(entry.getValue());
			expList.add(e);
		}
		return expList;
		
	}
	

}
