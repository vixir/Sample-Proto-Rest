package vi.practice.services;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.protobuf.*;
import org.springframework.web.client.*;
import protobuf.home.model.HomeProto.*;
import vi.practice.services.data.*;

import java.util.*;



@SpringBootApplication
public class HomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeServiceApplication.class, args);
    }

    @Bean
    @Primary
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }

    @Bean
    HomeRepository repository() {
        List<Home> homes = new ArrayList<>();
        homes.add(Home.newBuilder().setId(1).setOwnerId(1).setRooms("2BHK").build());
        homes.add(Home.newBuilder().setId(2).setOwnerId(2).setRooms("3BHK").build());
        homes.add(Home.newBuilder().setId(3).setOwnerId(3).setRooms("3BHK").build());
        homes.add(Home.newBuilder().setId(4).setOwnerId(4).setRooms("3BHK").build());
        homes.add(Home.newBuilder().setId(5).setOwnerId(1).setRooms("4BHK PENTHOUSE").build());
        homes.add(Home.newBuilder().setId(6).setOwnerId(2).setRooms("3BHK").build());
        homes.add(Home.newBuilder().setId(7).setOwnerId(2).setRooms("3BHK").build());
        return new HomeRepository(homes);
    }

}