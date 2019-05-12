package net.quadratic.service;

import net.quadratic.config.MessageSourceConfiguration;
import net.quadratic.dto.ParamsDto;
import net.quadratic.entity.Params;
import net.quadratic.dao.ParamsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParamsServiceImpl implements ParamsService {

    @Autowired
    private ParamsDAO paramsDAO;

    @Autowired
    private MessageSourceConfiguration message;

    @Override
    @Transactional
    public String save(ParamsDto dto) {
        if (dto.getA() == 0) {
            return message.getMessage("exception.a.is_zero");
        } else {
            double D = dto.getB() * dto.getB() - 4 * dto.getA() * dto.getC();
            Params params = new Params(dto);
            String responce;
            if (D > 0) {
                double x1, x2;
                x1 = (-dto.getB() - Math.sqrt(D)) / (2 * dto.getA());
                x2 = (-dto.getB() + Math.sqrt(D)) / (2 * dto.getA());
                params.setX1(x1);
                params.setX2(x2);
                responce = message.getMessage("message.success.two_results", new Object[]{x1, x2});
            } else if (D == 0) {
                double x;
                x = -dto.getB() / (2 * dto.getA());
                params.setX1(x);
                responce = message.getMessage("message.success.one_results", new Object[]{x});
            } else {
                responce = message.getMessage("message.success.no_results");
            }
            paramsDAO.save(params);

            return responce;
        }
    }
}
