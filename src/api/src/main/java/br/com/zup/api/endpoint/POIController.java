package br.com.zup.api.endpoint;

import br.com.zup.api.dto.POIDTO;
import br.com.zup.api.facade.IPOIFacade;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class POIController implements IPOIController {

    private final IPOIFacade facade;

    @Override
    public List<POIDTO> listAll() {
        return facade.listAll();
    }

    @Override
    public POIDTO create(POIDTO dto) {
        return facade.create(dto);
    }

    @Override
    public List<POIDTO> listAllByReferencePoint(int x, int y, double d) {
        return facade.listAllByReferencePoint(x, y, d);
    }
}
