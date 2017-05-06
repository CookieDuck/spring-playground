package com.example.controller;

import com.example.config.PlaygroundSecurityConfig;
import com.example.repo.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc
@ContextConfiguration
@Import(PlaygroundSecurityConfig.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository mockRepo;

    @Test
    public void testAdminRoleGrantsAccess() throws Exception {
        mvc.perform(get("/admin/employees")
                .with(user("boss").password("my-boss-password").roles("MANAGER", "ADMIN")))
                .andExpect(authenticated().withRoles("MANAGER", "ADMIN"))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    public void testUserRoleIsDenied() throws Exception {
        mvc.perform(get("/admin/employees"))
                .andExpect(authenticated().withRoles("EMPLOYEE"))
                .andExpect(status().is(403));
    }

    @Test
    @WithAnonymousUser
    public void testAnonymousIsDenied() throws Exception {
        mvc.perform(get("/admin/employees"))
                .andExpect(status().is(401));
    }
}
