package com.yfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yfs.po.FaceCapture;
import com.yfs.repository.FaceCaptureRepository;

@Service
public class FaceCaptureService {

	@Autowired
	private FaceCaptureRepository faceCaptureRepository;

	public List<FaceCapture> findAll() {
		return faceCaptureRepository.findAll();
	}

}
