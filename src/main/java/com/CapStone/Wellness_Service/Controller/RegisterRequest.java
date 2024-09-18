package com.CapStone.Wellness_Service.Controller;

public record RegisterRequest(String username, String password, String email, String [] roles) {
}
