package com.socialuni.center.test;

import com.socialuni.center.web.CenterWebApplication;
import com.socialuni.social.constant.GenderType;
import com.socialuni.center.web.mapper.TalkMapper;
import com.socialuni.center.web.mapper.TalkTagMapper;
import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.store.TalkQueryStore;
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
    @Resource
    TalkMapper talkMapper;
    @Resource
    TalkTagMapper talkTagMapper;
    @Resource
    TalkQueryStore talkQueryStore;


    @Test
    public void testJpa() {
//        this.test0();
//        this.test1();
        this.test2();
//        this.test3();
//        this.test4();
    }

    private void test4() {

        Date curDate = new Date();
        System.out.println("**************************************");
        List<Integer> list = talkRepository.queryTalkIdsByTagIdsAndTagVisibleGender(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("12312313");
        System.out.println("最终耗时:" + (System.currentTimeMillis() - curDate.getTime()));
        System.out.println("**************************************");
    }

    private void test3() {
        Date curDate = new Date();
        System.out.println("**************************************");
        List<Integer> list = talkTagMapper.queryTagTalkIdsByCom(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                //                null,
                null);

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("12312313");
        System.out.println("最终耗时:" + (System.currentTimeMillis() - curDate.getTime()));
        System.out.println("**************************************");
    }

    private void test2() {
        Date curDate = new Date();
        System.out.println("**************************************");

       /* List<Integer> list = talkQueryStore.queryUserTalkIdsByTab(
                108,
                null,
                -500,
                500,
                null,
                GenderType.all,
                null,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7), PageRequest.of(0, 10), 1);*/

        /*List<Integer> list = talkMapper.queryTalkIdsByCom(
                null,
                -500,
                500,
                "正常",
                null,
                GenderType.all,
                null,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                //                null,
                null);*/

//        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("12312313");
        System.out.println("最终耗时:" + (System.currentTimeMillis() - curDate.getTime()));
        System.out.println("**************************************");
    }

    private void test0() {
        Date curDate = new Date();

        // Step1：创建一个 QueryWrapper 对象


        // 链式调用 lambda 式。注意：不支持 Kotlin
        //667毫秒
        System.out.println("开始查询");
        List<Integer> list = talkRepository.queryTalkIdsByCom(
                null,
                -500,
                500,
                "正常",
                null,
                GenderType.all,
                null,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                //                null,
                null);

        System.out.println(Arrays.toString(list.toArray()));
        System.out.println("最终耗时:" + (System.currentTimeMillis() - curDate.getTime()));
    }

    private void test1() {
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
