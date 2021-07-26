package com.service.mstc.model;


import com.fasterxml.jackson.annotation.JsonProperty;
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
@Document("uploads")
public class Upload {
  @Id
  private String id;

  private String name;
  private String path;
  private String type;
  private String key;

  @JsonProperty("created_date")
  private Instant createdDate = Instant.now();
}
