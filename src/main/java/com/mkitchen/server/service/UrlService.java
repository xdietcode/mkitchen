package com.mkitchen.server.service;

import com.mkitchen.server.entity.AmazonUrl;
import com.mkitchen.server.repository.AmazonUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlService {

    @Autowired
    private AmazonUrlRepository urlRepository;

    public AmazonUrl getByName(String name) {

        return urlRepository.findByName(name);
    }

    public AmazonUrl save(AmazonUrl url) {
        return urlRepository.save(url);
    }

    public List<AmazonUrl> saveAll(List<AmazonUrl> aUrls) {
        return urlRepository.saveAll(aUrls);
    }
}
