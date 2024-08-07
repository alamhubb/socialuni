package com.socialuni.social.admin.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TestPinyin {
    public static void main(String[] args) {
        //发布的内容，转为拼音，关键词转为拼音，去除空格，去除各种符号，只保留汉字英文，
        //关键词也转为拼音
//        String str = "*adCVs*34_a _09_b5*[/435^*&城池()^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
        //1 去除特殊字符和空格
        //2 转为拼音
        //3 遍历关键词，看是否包含关键词

        String dsfa = "秦君庆、秦媚君、秦金君、秦琬君、秦咏君、秦念君、秦君微、秦君瑛、秦如君、秦鹤君、秦卿君、秦抒君、秦君棕、秦君然、秦君仕、秦君君、秦妮君、秦君妃、秦君乔、秦彤君、秦欢君、秦君瀚、秦子君、秦琪君、秦弥君、秦豫君、秦弘君、秦君芙、秦君城、秦君慧、秦君英、秦轩君、秦君游、秦君玫、秦君风、秦君韬、秦君德、秦君良、秦枫君、秦君璐、秦君若、秦君茹、秦君子、秦君弘、秦君崇、秦君姿、秦君钟、秦君彦、秦妤君、秦君青、秦君枝、秦君鹏、秦君冉、秦皖君、秦燕君、秦瑜君、秦君花、秦雨君、秦澄君、秦君娥、秦君慕、秦君攀、秦婵君、秦华君、秦君竹、秦君珍、秦君湘、秦君彤、秦君双、秦霄君、秦君浩、秦传君、秦翠君、秦君梓、秦君抒、秦泽君、秦萍君、秦如君、秦君布、秦君贞、秦辰君、秦君情、秦君俪、秦昱君、秦画君、秦君极、秦君含、秦琰君、秦哲君、秦君传、秦折君、秦君畅、秦芹君、秦君雷、秦君盼、秦君柯、秦绮君、秦君康、秦君逸、秦君岩、秦君珠、秦君瑜、秦君嫦、秦晋君、秦君霏、秦君万、秦维君、秦君昭、秦君真、秦听君、秦君谦、秦桐君、秦嘉君、秦瑾君、秦锦君、秦君婼、秦昭君、秦茂君、秦锘君、秦杭君、秦易君、秦施君、秦君楷、秦君南、秦君薇、秦燨君、秦君文、秦君泽、秦国君、秦君孝、秦君茂、秦常君、秦君白、秦君潍、秦珺君、秦波君、秦滟君、秦娈君、秦元君、秦君哲、秦君志、秦君棋、秦君霄、秦君煜、秦君沁、秦君南、秦彤君、秦君予、秦君菡、秦育君、秦君煊、秦君苹、秦戈君、秦宇君、秦谷君、秦君贤、秦黛君、秦海君、秦璟君、秦君高、秦歆君、秦君美、秦君裕、秦振君、秦碧君、秦君贻、秦君妙、秦锋君、秦君广、秦君楚、秦云君、秦君媛、秦君馨、秦琦君、秦新君、秦君瑞、秦君阔、秦志君、秦勤君、秦君寻、秦境君、秦君煦、秦君靖、秦君观、秦君唤、秦嫔君、秦忠君、秦君京、秦君楠、秦商君、秦君旭、秦君诺、秦君修、秦君容、秦茜君、秦君桐、秦君懿、秦馥君、秦君贻、秦君仪、秦君鸿、秦杉君、秦君月、秦君殷、秦君园、秦怜君、秦霜君、秦君龙、秦君亿、秦妹君、秦眉君、秦凤君、秦君悠、秦溪君、秦南君、秦世君、秦擎君、秦君初、秦炫君、秦音君、秦君琪、秦毓君、秦怀君、秦雁君、秦治君、秦君春、秦君芊、秦烟君、秦君聚、秦知君、秦刚君、秦君磊、秦君涛、秦君远、秦君苑、秦君明、秦宜君、秦君岚、秦君紫、秦君晓、秦君霜、秦君奕、秦君渊、秦君柔、秦妙君、秦君鼎、秦君雯、秦君优、秦若君、秦君蓝、秦龙君、秦奕君、秦秀君、秦资君、秦姣君、秦冠君、秦庆君、秦君影、秦君深、秦青君、秦君思、秦炎君、秦会君、秦玥君、秦君擎、秦孝君、秦宛君、秦荭君、秦茗君、秦驰君、秦君欢、秦君盛、秦君毓、秦斌君、秦瑷君、秦君若、秦君晋、秦君云、秦勤君、秦杰君、秦城君、秦荣君、秦瑗君、秦君任、秦君虎、秦微君、秦君翎、秦君格、秦君羽、秦烨君、秦君驰、秦唤君、秦禹君、秦尚君、秦君伶、秦君菲、秦容君、秦珠君、秦瑛君、秦心君、秦珊君、秦昭君、秦慕君、秦越君、秦嫱君、秦姿君、秦君庸、秦君业、秦君泉、秦君莹、秦君逸、秦君胜、秦绰君、秦雯君、秦君水、秦馨君、秦君引、秦琼君、秦君沛、秦莺君、秦平君、秦君芃、秦君飘、秦傲君、秦新君、秦君阳、秦艳君、秦松君、秦琳君、秦君璧、秦凡君、秦芷君、秦君金、秦珍君、秦君萧、秦君馥、秦超君、秦君偲、秦萌君、秦函君、秦君刚、秦君芝、秦君实、秦君丹、秦君冰、秦棕君、秦琪君、秦君轩、秦欧君、秦君灵、秦羽君、秦滢君、秦远君、秦霖君、秦露君、秦君圣、秦雪君、秦浩君、秦君安、秦君耿、秦颖君、秦轩君、秦君莲、秦君永、秦宏君、秦辉君、秦君谷、秦音君、秦君宸、秦尚君、秦君懿、秦君炫、秦君琳、秦君昱、秦悦君、秦真君、秦君如、秦君承、秦君眉、秦君勋、秦君尊、秦萧君、秦君蝶、秦君存、秦君艳、秦达君、秦君益、秦伶君、秦君舒、秦君琦、秦君原、秦寒君、秦彬君、秦俪君、秦瑶君、秦君皖、秦君婉、秦君文、秦予君、秦畅君、秦君娈、秦旭君、秦晶君、秦祥君、秦源君、秦民君、秦君钗、秦君谷、秦玮君、秦优君、秦德君、秦婉君、秦星君、秦君智、秦橙君、秦君江、秦鸿君、秦君漪、秦君碧、秦含君、秦娅君、秦君贝、秦敬君、秦君会、秦君桐、秦展君、秦君彬、秦君红、秦里君、秦君昂、秦君锦、秦君香、秦娆君、秦君际、秦君瑗、秦曦君、秦檀君、秦乐君、秦金君、秦月君、秦淑君、秦谦君、秦苹君、秦慧君、秦君莎、秦布君、秦君凝、秦君亭、秦蕙君、秦君凡、秦楠君、秦文君、秦君枫、秦融君、秦旋君、秦君豫、秦荣君、秦银君、秦琦君、秦昂君、秦青君、秦君怜、秦君淼、秦君流、秦君菱、秦君轩、秦君伟、秦初君、秦珂君、秦君荭、秦岚君、秦君珊、秦君霜、秦香君、秦君耀、秦君生、秦君娆、秦兰君、秦凌君、秦姬君、秦君波、秦图君、秦卉君、秦殷君、秦君展、秦君平、秦荔君、秦峻君、秦君颜、秦晓君、秦桐君、秦君雁、秦君尚、秦君萌、秦君悦、秦君载、秦阳君、秦良君、秦竹君、秦君晟、秦君蓉、秦雪君、秦盈君、秦君欣、秦飘君、秦君福、秦溪君、秦梓君、秦春君、秦淼君、秦霞君、秦君韵、秦浅君、秦君乐、秦君鉴、秦影君、秦君炎、秦君祺、秦崇君、秦婕君、秦爱君、秦仪君、秦岩君、秦磊君、秦君兴、秦君翠、秦玫君、秦君澜、秦君全、秦飘君、秦流君、秦君惠、秦君梅、秦含君、秦君珂、秦君艺、秦君劲、秦俞君、秦菱君、秦君溪、秦光君、秦君祥、秦君怀、秦君心、秦昌君、秦璐君、秦瑾君、秦君秋、秦楚君、秦君亚、秦惜君、秦君颖、秦君叆、秦缨君、秦君莺、秦蕾君、秦伟君、秦君寒、秦勋君、秦君慧、秦君研、秦莉君、秦君古、秦海君、秦君浅、秦君久、秦君洁、秦君呤、秦君滢、秦君晴、秦诗君、秦君鑫、秦靖君、秦君姬、秦久君、秦君苑、秦君瑾、秦极君、秦亿君、秦妩君、秦君弘、秦韬君、秦萱君、秦娥君、秦歌君、秦君蕾、秦君姣、秦君朋、秦君芷、秦君妤、秦致君、秦蔓君、秦思君、秦邦君、秦阔君、秦君妮、秦迁君、秦君妹、秦君信、秦君恒、秦君杰、秦全君、秦夕君、秦君菁、秦双君、秦君娜、秦君泽、秦君兼、秦君姞、秦爽君、秦君翰、秦君纯、秦俊君、秦来君、秦君治、秦乔君、秦皓君、秦世君、秦君尧、秦偲君、秦芸君、秦君倚、秦君荣、秦弘君、秦震君、秦君萍、秦君凌、秦晟君、秦侠君、秦谷君、秦君蓓、秦广君、秦君宇、秦璇君、秦然君、秦君惜、秦君桃、秦君融、秦君野、秦君孝、秦君镇、秦君傲、秦赫君、秦际君、秦君来、秦励君、秦镒君、秦初君、秦君语、秦孝君、秦玲君、秦湘君、秦君向、秦勇君、秦君维、秦临君、秦君姗、秦琴君、秦依君、秦君雨、秦凯君、秦羽君、秦尧君、秦贻君、秦君禹、秦君宇、秦君靖、秦君菊、秦君婷、秦君嫔、秦生君、秦可君、秦君嘉、秦君依、秦游君、秦君嫣、秦君黛、秦君翱、秦君娟、秦君嘉、秦芹君、秦君俊、秦君宜、秦君图、秦载君、秦秋君、秦君聪、秦君葶、秦君玉、秦君莉、秦君纨、秦君秀、秦诚君、秦修君、秦君芹、秦君玉、秦观君、秦朋君、秦君勤、秦澜君、秦君如、秦君源、秦君拓、秦君易、秦萌君、秦葶君、秦贤君、秦君荣、秦卡君、秦容君、秦丽君、秦鑫君、秦漾君、秦银君、秦君檀、秦君初、秦赫君、秦君陌、秦君楚、秦君勤、秦君玄、秦君宁、秦君栩、秦业君、秦童君、秦白君、秦君茗、秦聪君、秦贤君、秦山君、秦君娴、秦明君、秦莲君、秦君健、秦君夕、秦君忆、秦清君、秦君霖、秦君巧、秦楠君、秦君烟、秦君彰、秦婼君、秦君瑞、秦娉君、秦君采、秦凡君、秦君梦、秦君尚、秦君可、秦诺君、秦君澄、秦祺君、秦君新、秦桂君、秦思君、秦影君、秦君旭、秦君育、秦纨君、秦潍君、秦尘君、秦月君、秦君常、秦江君、秦君宛、秦泽君、秦啸君、秦君凡、秦君橙、秦君岩、秦君兰、秦栋君、秦君元、秦君筠、秦君玲、秦君婧、秦君阳、秦君昭、秦庸君、秦媛君、秦君卿、秦南君、秦园君、秦格君、秦君璇、秦君弥、秦悠君、秦文君、秦君娇、秦柯君、秦君敏、秦鹏君、秦梦君、秦君豪、秦君昌、秦茹君、秦熙君、秦君雍、秦君云、秦沁君、秦君姝、秦忆君、秦君勇、秦君环、秦苏君、秦悠君、秦怀君、秦君运、秦君荔、秦美君、秦君欧、秦拓君、秦君君、秦蓓君、秦健君、秦君婵、秦君进、秦恒君、秦翎君、秦霖君、秦煜君、秦君漫、秦君桦、秦宸君、秦君淘、秦君芳、秦景君、秦逸君、秦兴君、秦君凤、秦安君、秦君鑫、秦君霖、秦君泰、秦裕君、秦沛君、秦梅君、秦君嫱、秦君蕊、秦君妩、秦君月、秦瑞君、秦春君、秦君灏、秦懿君、秦启君、秦君雨、秦靖君、秦素君、秦楚君、秦君佑、秦贝君、秦艺君、秦原君、秦君熙、秦君琰、秦君绰、秦柏君、秦君松、秦君启、秦君画、秦钟君、秦君漾、秦嫦君、秦渊君、秦蝶君、秦君悟、秦姗君、秦煦君、秦君振、秦君恩、秦材君、秦娟君、秦君璋、秦君旋、秦懿君、秦君玲、秦菁君、秦硕君、秦君寒、秦欢君、秦采君、秦代君、秦淘君、秦莎君、秦然君、秦攀君、秦丹君、秦英君、秦涵君、秦君基、秦君景、秦君绮、秦君清、秦君里、秦君爽、秦君欢、秦君银、秦岩君、秦君星、秦怡君、秦君斌、秦姝君、秦芊君、秦君蔓、秦灵君、秦枝君、秦莹君、秦劲君、秦韵君、秦君睿、秦仕君、秦君海、秦彦君、秦君歆、秦君瑶、秦翊君、秦君商、秦智君、秦舒君、秦弈君、秦君峻、秦君夏、秦煜君、秦君苏、秦君帆、秦语君、秦若君、秦君知、秦君音、秦灏君、秦君锘、秦佑君、秦君少、秦古君、秦君瑜、秦鼎君、秦君筱、秦芯君、秦旭君、秦君咏、秦芝君、秦君壬、秦君伊、秦实君、秦福君、秦君棋、秦君芬、秦红君、秦建君、秦君伦、秦君山、秦君溪、秦君娉、秦君垣、秦君涵、秦筱君、秦君芯、秦蓝君、秦君琦、秦聚君、秦君素、秦棋君、秦存君、秦君临、秦荷君、秦君杭、秦芙君、秦君鹤、秦唯君、秦筠君、秦静君、秦高君、秦伦君、秦君宝、秦花君、秦恩君、秦君赫、秦婷君、秦倩君、秦雍君、秦君代、秦君春、秦尊君、秦瑞君、秦君曦、秦君方、秦君金、秦君爱、秦君听、秦君喻、秦敏君、秦君聪、秦君卉、秦情君、秦君颜、秦曼君、秦君忆、秦君华、秦佩君、秦君娅、秦翰君、秦陌君、秦众君、秦君荷、秦君昊、秦君雅、秦菊君、秦彩君、秦漪君、秦沫君、秦君新、秦君玥、秦君烨、秦君念、秦君润、秦晗君、秦环君、秦盼君、秦聪君、秦倚君、秦研君、秦君石、秦君建、秦君海、秦进君、秦水君、秦娴君、秦君霞、秦君民、秦君缨、秦语君、秦君影、秦璋君、秦君啸、秦君怡、秦姞君、秦虎君、秦颜君、秦君才、秦君材、秦引君、秦泉君、秦君众、秦玉君、秦润君、秦慧君、秦君悠、秦君忠、秦石君、秦君晶、秦耿君、秦婧君、秦雨君、秦君婕、秦闻君、秦君励、秦君俞、秦胜君、秦君辉、秦君凯、秦君诗、秦君萌、秦鑫君、秦君妍、秦君晓、秦晓君、秦柔君、秦君越、秦君娣、秦益君、秦锦君、秦君诚、秦君国、秦君淑、秦君源、秦玄君、秦野君、秦桃君、秦君翊、秦忆君、秦君敬、秦君辰、秦君珺、秦茵君、秦君亿、秦亚君、秦苑君、秦君媚、秦君虹、秦君雪、秦康君、秦苑君、秦源君、秦君博、秦君芸、秦涛君、秦君栋、秦宇君、秦冰君、秦君唯、秦壬君、秦玉君、秦君琴、秦璧君、秦叆君、秦君燕、秦君倩、秦君琬、秦豪君、秦君仁、秦宝君、秦任君、秦逸君、秦君贤、秦欣君、秦亿君、秦君宏、秦星君、秦君思、秦君强、秦圣君、秦耀君、秦娜君、秦楷君、秦佳君、秦妍君、秦君壮、秦君绍、秦翱君、秦云君、秦深君、秦垣君、秦晨君、秦虹君、秦钗君、秦君希、秦君戈、秦君茜、秦瀚君、秦嫣君、秦君冠、秦君函、秦鉴君、秦君瑾、秦贻君、秦惠君、秦君震、秦君施、秦君玮、秦君露、秦颜君、秦君硕、秦玲君、秦纯君、秦伊君、秦君柏、秦雷君、秦君茵、秦君翌、秦信君、秦君睿、秦君萱、秦阳君、秦紫君、秦君煜、秦君锋、秦基君、秦壮君、秦君世、秦棋君、秦盛君、秦君银、秦君蕙、秦晴君、秦君芹、秦君飘、秦凝君、秦瑜君、秦万君、秦幻君、秦芮君、秦翌君、秦镇君、秦嘉君、秦君璟、秦君闻、秦君怀、秦泰君、秦君资、秦君盈、秦维君、秦君曼、秦霭君、秦君彤、秦娣君、秦呤君、秦强君、秦君锦、秦言君、秦菡君、秦方君、秦芃君、秦君晨、秦君瑷、秦睿君、秦君沫、秦雅君、秦博君、秦蕊君、秦君琼、秦君致、秦夏君、秦君琪、秦才君、秦永君、秦君霭、秦浪君、秦芳君、秦霜君、秦君赫、秦漫君、秦薇君、秦君星、秦君尘、秦希君、秦君弈、秦冉君、秦寻君、秦承君、秦君桂、秦绍君、秦君然、秦帆君、秦君侠、秦向君、秦君光、秦巧君、秦亭君、秦清君、秦君世、秦彰君、秦少君、秦妃君、秦喻君、秦君镒、秦君音、秦君清、秦昊君、秦君浪、秦悟君、秦仁君、秦君杉、秦君超、秦君佳、秦菲君、秦君折、秦君容、秦君羽、秦栩君、秦煊君、秦君境、秦君天、秦君歌、秦君维、秦君皓、秦君雪、秦君卡、秦君燨、秦霏君、秦君丽、秦运君、秦宁君、秦京君、秦君静、秦寒君、秦君晗、秦蓉君、秦娇君、秦君达、秦风君、秦君青、秦睿君、秦君幻、秦君含、秦贞君、秦君楠、秦君言、秦洁君、秦君佩、秦兼君、秦君童、秦芬君、秦君语、秦君邦、秦桦君、秦君滟、秦君彩、秦君迁、秦天君、秦君芮、";
        String[] list = dsfa.split("");
        List<String> adf = new ArrayList<>();
        Map<String, String> stringStringMap = new HashMap<>();
        for (String s : list) {
            if (!Arrays.asList("秦", "、").contains(s)) {
                stringStringMap.put(s, s);
            }
        }
        for (String s : stringStringMap.keySet()) {
            adf.add(s);
        }
        System.out.println(adf.toString());
        String chineseCharacter = "汉"; // 要获取笔画数量的汉字
        int strokeCount = getStrokeCount(chineseCharacter);
        System.out.println("汉字 " + chineseCharacter + " 的笔画数量为：" + strokeCount);

    }

    public static int getStrokeCount(String chineseCharacter) {
        int strokeCount = 0;
        char[] charArray = chineseCharacter.toCharArray();
        for (char c : charArray) {
            strokeCount += getStrokeCountOfCharacter(c);
        }
        return strokeCount;
    }

    public static int getStrokeCountOfCharacter(char c) {
        if (isChineseCharacter(c)) {
            int unicode = c;
            // 根据 Unicode 编码范围判断笔画数量
            if (unicode >= 0x4E00 && unicode <= 0x9FA5) {
                // 在 Unicode 编码范围内的汉字，可以通过以下公式计算笔画数量
                // 具体的笔画数量对应关系可以参考汉字的笔画数据库或者笔画查询工具
                return (unicode <= 0x9FBB) ? 1 : 2;
            }
        }
        return 0;
    }

    public static boolean isChineseCharacter(char c) {
        // 判断字符是否为汉字
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`_~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim().replaceAll(" ", "");
    }
}
