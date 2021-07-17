package com.service.mstc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.mstc.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document("articles")
public class Article {
  @Id
  private String id;

  private String title;
  private String slug;
  private String description;
  private String content;

  private Status status;

  @JsonProperty("image_url")
  private String imageUrl;

  @JsonProperty("created_date")
  private Instant createdDate = Instant.now();

  @JsonProperty("updated_date")
  private Instant updatedDate;
}
