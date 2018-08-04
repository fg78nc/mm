package com.fg7;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@RunWith(SpringRunner.class)
public class CustomerServiceApplicationTest {

    @Value("${example.property}")
    String exampleProperty;


//    // Run only if config server is up and running
//    @Test
//    public void configServerQueryShouldReturnData() {
//        assertThat(this.exampleProperty).isEqualTo("config-server-ok");
//    }


}