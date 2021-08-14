package com.service.mstc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("products")
public class Product {
  @Id
  private String id;

  private String name;
  private String model;
  private String brand;
  private List<String> country;
  private String category;

  @JsonProperty("image_url")
  private String imageUrl;
}
