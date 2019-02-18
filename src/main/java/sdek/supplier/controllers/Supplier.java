package sdek.supplier.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import sdek.supplier.utils.RequestSocket;
import sdek.supplier.utils.ResponseSocket;
import sdek.supplier.utils.ResponseSocketBuilder;

@Controller
public class Supplier {

    @MessageMapping("/canceledOrder")
    @SendTo("/topic/canceled")
    public ResponseSocket canceledOrder(RequestSocket requestSocket) throws Exception {
        return ResponseSocketBuilder.build(requestSocket);
    }

}
