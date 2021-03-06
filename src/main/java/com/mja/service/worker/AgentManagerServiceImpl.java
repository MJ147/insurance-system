package com.mja.service.worker;

import com.mja.dao.worker.AgentManagerDao;
import com.mja.model.worker.AgentManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentManagerServiceImpl implements AgentManagerService {

    AgentManagerDao agentManagerDao;

    public AgentManagerServiceImpl(AgentManagerDao agentManagerDao) {
        this.agentManagerDao = agentManagerDao;
    }

    @Override
    public AgentManager save(AgentManager insuranceAgent) {
        return agentManagerDao.save(insuranceAgent);
    }

    @Override
    public List<AgentManager> removeById(Long id) {
        return agentManagerDao.removeById(id);
    }

    @Override
    public List<AgentManager> findAll() {
        return agentManagerDao.findAll();
    }
}
