// package com.example;
package com.example.restaurantrater;

// import com.example.restaurantrater.entities.Restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.boot.SpringApplication;





@SpringBootApplication
@EntityScan(basePackages = "com.example.restaurantrater")
public class RestaurantraterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantraterApplication.class, args);

		// Restaurant rest = new Restaurant();
	}

}



// package com.example;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class RestaurantraterApplication {

//     public static void main(String[] args) {
//         SpringApplication.run(RestaurantraterApplication.class, args);
//     }
// }
