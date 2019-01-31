package sdek.supplier.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sdek.supplier.mappers.Dictionary;


@RestController
public class Supplier {
    /**
     * метод возращает формат данных вносимый в базу PostgreSQL
     * */
    @RequestMapping(value = "/add/canceledOrder",method = RequestMethod.POST)
    public Dictionary addCatalogSatelliteImage(
            @RequestParam(value="numOrder", defaultValue="none") String numOrder
    ) {

        return null;

    }

}
