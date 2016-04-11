package org.liufeng.course.kuaidi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.liufeng.course.util.ServicesUtil;

public class Search {
	@Test
	public String search(String content) {
		

		/**
		 * Express Map
		 */
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("aae", "AAE���");
		map.put("ande", "��������");
		map.put("anjie", "���ݿ��");
		map.put("anneng", "��������");
		map.put("anxun", "��Ѹ����");
		map.put("aoshuo", "��˶����");
		map.put("aramex", "Aramex���ʿ��");
		map.put("baiqian", "��ǧ�Ϲ�������");
		map.put("balunzhi", "����֧");
		map.put("baotongda", "��ͨ��");
		map.put("benteng", "�ɶ����ڹ��ʿ��");
		map.put("changtong", "��ͨ����");
		map.put("chengguang", "�̹���");
		map.put("chengji", "�Ǽʿ��");
		map.put("chengshi100", "����100");
		map.put("chuanxi", "��ϲ���");
		map.put("chuanzhi", "��־���");
		map.put("chukouyi", "����������");
		map.put("citylink", "CityLinkExpress");
		map.put("coe", "�������");
		map.put("coscon", "�й�Զ������(COSCON)");
		map.put("cszx", "����֮��");
		map.put("dada", "�������");
		map.put("dajin", "�������");
		map.put("dashun", "��˳����");
		map.put("datian", "��������");
		map.put("dayang", "�����������");
		map.put("debang", "�°�����");
		map.put("dhl", "DHL���");
		map.put("diantong", "��ͨ���");
		map.put("disifang", "���ķ��ٵ�");
		map.put("dpex", "DPEX���");
		map.put("dsu", "D�ٿ��");
		map.put("ees", "�ٸ���������");
		map.put("ems", "EMS���");
		map.put("eyoubao", "E�ʱ�");
		map.put("fangfangda", "����������");
		map.put("fanyu", "������");
		map.put("fardar", "Fardar");
		map.put("fedex", "����Fedex");
		map.put("fedexcn", "Fedex����");
		map.put("feibao", "�ɱ����");
		map.put("feihang", "ԭ�ɺ�����");
		map.put("feite", "��������");
		map.put("feiyang", "������");
		map.put("feiyuan", "��Զ����");
		map.put("fengda", "�����");
		map.put("gangkuai", "�ۿ��ٵ�");
		map.put("gaotie", "�������");
		map.put("gdyz", "�㶫��������");
		map.put("gnxb", "����С��");
		map.put("gongsuda", "���ٴ�����|���");
		map.put("guanda", "�ڴ���");
		map.put("guotong", "��ͨ���");
		map.put("haihong", "ɽ��������");
		map.put("haolaiyun", "�����˿��");
		map.put("haosheng", "�ʢ����");
		map.put("hebeijianhua", "�ӱ��������");
		map.put("henglu", "��·����");
		map.put("hengyu", "������ͨ");
		map.put("hkpost", "�������");
		map.put("huacheng", "��������");
		map.put("huahan", "��������");
		map.put("huahang", "�������");
		map.put("huangmajia", "�����׿��");
		map.put("huaqi", "������");
		map.put("huayu", "��������");
		map.put("huitong", "��ͨ���");
		map.put("hutong", "��ͨ����");
		map.put("hwhq", "���⻷����");
		map.put("intmail", "�����������");
		map.put("jiahuier", "�ѻݶ����");
		map.put("jiaji", "�Ѽ�����");
		map.put("jiayi", "��������");
		map.put("jiayu", "��������");
		map.put("jiayunmei", "���������");
		map.put("jiete", "���ؿ��");
		map.put("jinda", "�������");
		map.put("jingdong", "�������");
		map.put("jingguang", "������");
		map.put("jingshi", "��������");
		map.put("jinyue", "��Խ���");
		map.put("jiuyi", "���׿��");
		map.put("jixianda", "���ȴ�����");
		map.put("jldt", "�����ͨ����");
		map.put("jppost", "�ձ�����");
		map.put("kcs", "˳��(KCS)���");
		map.put("kuaijie", "��ݿ��");
		map.put("kuaitao", "�����ٵ�");
		map.put("kuaiyouda", "���Ŵ��ٵ�");
		map.put("kuanrong", "��������");
		map.put("kuayue", "��Խ���");
		map.put("lanhu", "�������");
		map.put("lejiedi", "�ֽݵݿ��");
		map.put("lianhaotong", "���ͨ���");
		map.put("lijisong", "�ɶ������Ϳ��");
		map.put("lindao", "�Ϻ��ֵ�����");
		map.put("longbang", "������");
		map.put("menduimen", "�Ŷ��ſ��");
		map.put("mengsu", "���ٿ��");
		map.put("minbang", "�����");
		map.put("mingliang", "��������");
		map.put("minsheng", "��ʢ���");
		map.put("nanbei", "�ϱ����");
		map.put("nell", "������");
		map.put("nengda", "�ܴ���");
		map.put("nsf", "��˳�ᣨNSF�����");
		map.put("ocs", "OCS���");
		map.put("peixing", "��������");
		map.put("pinganda", "ƽ����");
		map.put("pingyou", "�й�����");
		map.put("ppbyb", "���ʱ�");
		map.put("quanchen", "ȫ�����");
		map.put("quanfeng", "ȫ����");
		map.put("quanritong", "ȫ��ͨ���");
		map.put("quanyi", "ȫһ���");
		map.put("ririshun", "����˳����");
		map.put("riyu", "��������");
		map.put("rongqing", "��������");
		map.put("rpx", "RPX��ʱ��");
		map.put("rufeng", "������");
		map.put("ruifeng", "����ٵ�");
		map.put("saiaodi", "���ĵ�");
		map.put("santai", "��̬�ٵ�");
		map.put("scs", "ΰ��(SCS)���");
		map.put("shengan", "ʥ������");
		map.put("shengbang", "�ɰ�����");
		map.put("shengfeng", "ʢ������");
		map.put("shenghui", "ʢ������");
		map.put("shentong", "��ͨ���");
		map.put("shiyun", "���˿��");
		map.put("shunfeng", "˳����");
		map.put("simai", "˼�����");
		map.put("singpost", "�¼�������");
		map.put("suchengzhaipei", "�ٳ�լ��");
		map.put("suijia", "�������");
		map.put("sure", "�ٶ����");
		map.put("suteng", "���ڿ��");
		map.put("sutong", "��ͨ����");
		map.put("tiantian", "������");
		map.put("tnt", "TNT���");
		map.put("tongzhishu", "�߿�¼ȡ֪ͨ��");
		map.put("ucs", "�����ٵ�");
		map.put("ups", "UPS���");
		map.put("usps", "USPS���");
		map.put("wanbo", "�򲩿��");
		map.put("wanjia", "�������");
		map.put("wanxiang", "��������");
		map.put("weitepai", "΢����");
		map.put("wuhuan", "�廷�ٵ�");
		map.put("xianglong", "������ͨ���");
		map.put("xinbang", "�°�����");
		map.put("xinfeng", "�ŷ���");
		map.put("xingchengzhaipei", "�ǳ�լ����");
		map.put("xiyoute", "ϣ���ؿ��");
		map.put("yad", "Դ������");
		map.put("yafeng", "�Ƿ���");
		map.put("yanwen", "��������");
		map.put("yibang", "һ����");
		map.put("yinjie", "˳�ݷ����");
		map.put("yishunhang", "��˳�����");
		map.put("yousu", "���ٿ��");
		map.put("ytfh", "����һͳ�ɺ���");
		map.put("yuancheng", "Զ������");
		map.put("yuantong", "Բͨ���");
		map.put("yuefeng", "Խ����");
		map.put("yuhong", "�������");
		map.put("yumeijie", "�����ݿ��");
		map.put("yunda", "�ϴ���");
		map.put("yuntong", "��ͨ�иۿ��");
		map.put("zengyi", "������");
		map.put("zhaijisong", "լ���Ϳ��");
		map.put("zhengzhoujianhua", "֣�ݽ������");
		map.put("zhima", "֥�鿪�ſ��");
		map.put("zhongtian", "������������");
		map.put("zhongtie", "��������");
		map.put("zhongtong", "��ͨ���");
		map.put("zhongxinda", "���Ŵ���");
		map.put("zhongyou", "��������");
		map.put("zongxing", "��������");
		map.put("ztwl", "��������");
		map.put("zuochuan", "��������");
		
		ServicesUtil servicesUtil = new ServicesUtil();
		 String expName =null;
		if (servicesUtil.service(content)) {

			URLConnection connection = null;
			{

				try {

					String[] value = content.split("\\+");
					
					System.out.println(value[0]);
					Set<String> set = map.keySet();
					for(String ks : set){
						if(value[0].equals(map.get(ks))){
							expName = ks;
							System.out.println(ks);
						}
							
					}
					System.out.println(value[1]);
					connection = new URL(
							"http://api.ickd.cn/?id=107047&secret=5040f93fc211cab86ad3cf0d6d51def8&com="
									+ expName + "&nu=" + value[1]
									+ "&type=text&encode=utf8&ord=desc")
							.openConnection();
					connection.connect();

					InputStream fin = connection.getInputStream();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(fin, "UTF-8"));
					StringBuffer buffer = new StringBuffer();
					String temp = null;
					while ((temp = br.readLine()) != null) {
						
						buffer.append(temp + "\n");

					}
					return buffer.toString();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "�Բ��������������������Ӧ����Ϣ������������ظ��������֣������ѯ�����ظ� �������+��ݵ��� ���磺zhaijisong+4913588568��";
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(new Search().search("����С��+9976616915749"));
		long end = System.currentTimeMillis();

		System.out.println(new Search().search("����"));
		System.out.println(end - start);
	}
}