package com.socialuni.center.test;

import com.socialuni.center.web.CenterWebApplication;
import com.socialuni.center.web.controller.upgrade0823.TestUnionIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2019-05-25 10:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CenterWebApplication.class)
public class UnionIdTest {

    @Resource
    TestUnionIdService testUnionIdService;

    @Test
    public void testJpa() {
        testUnionIdService.unionid();
    }
}
