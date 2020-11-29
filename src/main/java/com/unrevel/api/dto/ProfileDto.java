package com.unrevel.api.dto;

import com.unrevel.api.model.RelevantQsAns;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProfileDto {
    private String profileImageUrl;
    private List<String> socialMediaLinks;
    private List<RelevantQsAns> relevantQsAnsList;

}
