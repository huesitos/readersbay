package com.groupfour.readersbay.service;

import com.groupfour.readersbay.repository.ReflectionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class ReflectionServiceImpl {

  @Autowired
  ReflectionRepository reflectionRepository;

}
