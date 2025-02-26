package com.kamelet.webhook.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhatsappWebhookController {
	
	private static final String VERIFY_TOKEN =  "KAMELETINTEGRATION";
	
	
	@GetMapping("/webhook")
	public ResponseEntity<String> veriyWebhook(@RequestParam("hub.mode") String mode,
			@RequestParam("hub.challenge") String challenge,
			@RequestParam("hub.verify_token") String veriyToken){
		
		System.out.println("verfication request received");
		System.out.println("mode: "+mode);
		System.out.println("challenge: "+challenge);
		System.out.println("token: "+veriyToken);
		
		if("subscribe".equalsIgnoreCase(mode) && veriyToken.equals(VERIFY_TOKEN)) {
			System.out.println("verification done");
			return ResponseEntity.ok(challenge);
		}
		else {
			System.out.println("verification could not be done");
			return ResponseEntity.status(403).body("Unauthorized");
		}
		
	}
	
	public ResponseEntity<String> receiveMessage(@RequestBody Map<String, Object> payload){
		System.out.println("Received payload");
		return ResponseEntity.ok("EVENT_RECEIVED");
	}
	
	

}
