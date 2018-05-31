package com.androidwatcher.service;

import com.androidwatcher.dao.RsaKeyMapper;
import com.androidwatcher.model.RsaKey;
import com.androidwatcher.util.RsaUtil;
import com.androidwatcher.vo.RsaVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.util.Random;

@Service
public class RsaService {

    private Random random=new Random();

    @Resource
    RsaKeyMapper rsaKeyMapper;

    public RsaVo get(){
        KeyPair keyPair = RsaUtil.genKeyPair(random.nextInt(1000) + 1024);
        RsaKey rsaKey=new RsaKey();
        rsaKey.setPrivateKey(RsaUtil.keyToString(keyPair.getPrivate()));
        rsaKey.setPublicKey(RsaUtil.keyToString(keyPair.getPublic()));
        rsaKey.setStatus(1);
        int insert = rsaKeyMapper.insert(rsaKey);
        RsaVo rsaVo=new RsaVo();
        rsaVo.setId(rsaKey.getId());
        rsaVo.setPublicKey(RsaUtil.keyToString(keyPair.getPublic()));
        return rsaVo;
    }
}
