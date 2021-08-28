package com.socialuni.center.test;

import com.socialuni.center.web.CenterWebApplication;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.sdk.repository.TalkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-05-25 10:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CenterWebApplication.class)
public class TalkQueryTest {

    @Resource
    TalkRepository talkRepository;

    @Test
    public void testJpa() {
       this.test1();
       this.test0();
    }

    private void test0(){
        Date curDate = new Date();

        System.out.println("开始查询");
        List<Integer> list = talkRepository.queryTalkIdsByCom(
                null,
                -500,
                500,
                "正常",
                null,
                GenderType.all,
                null,
                null,
                null);

//        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("最终耗时:" + (System.currentTimeMillis() - curDate.getTime()));
    }

    private void test1(){
        Date curDate = new Date();

        System.out.println("开始查询");
//        List<Integer> list = talkRepository.queryTalkIdsByCom(
        List<Integer> list = talkRepository.queryTalkIdsByComPageable(
                null,
                -500,
                500,
                "正常",
                null,
                GenderType.all,
                null,
                null,
                null, PageRequest.of(0, 10));

//        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("最终耗时:" + (System.currentTimeMillis() - curDate.getTime()));
    }
}
