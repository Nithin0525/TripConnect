package com.travelbuddy.post.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Count {
  private Integer maleCount;
  private Integer femaleCount;
  private Integer otherCount;
  private Integer totalCount() {
    return maleCount + femaleCount + otherCount;
  }
}
