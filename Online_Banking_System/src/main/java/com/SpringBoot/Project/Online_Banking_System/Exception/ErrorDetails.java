package com.SpringBoot.Project.Online_Banking_System.Exception;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp, String message,String details,String errorCode) {

}
