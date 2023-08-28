package com.thevarungupta.blog.rest.api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ErrorDetail {
    private Date timeStamp;
    private String message;
    private String details;
}
