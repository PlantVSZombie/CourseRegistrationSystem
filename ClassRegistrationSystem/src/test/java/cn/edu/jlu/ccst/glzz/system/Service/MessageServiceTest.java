package cn.edu.jlu.ccst.glzz.system.Service;

import cn.edu.jlu.ccst.glzz.system.Model.UserType;
import cn.edu.jlu.ccst.glzz.system.generated.Model.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceTest {
    @Resource
    MessageService messageService;

    @Test
    void sendMessage() {
        messageService.sendMessage("00128", UserType.Student,"myfirstMessage");
    }
}