package com.yfs.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yfs.po.FaceCapture;
import com.yfs.service.FaceCaptureService;

@RestController
public class FaceCaptureController {

	@Autowired
	private FaceCaptureService faceCaptureService;

	@RequestMapping("/")
	public List<FaceCapture> findAll() {
		return faceCaptureService.findAll();
	}

}
