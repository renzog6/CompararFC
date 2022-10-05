package ar.nex.factura;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Renzo
 */
public class CompararFC {

    public CompararFC() {
    }

    public CompararFC(List<Factura> sistema, List<Factura> afip) {

        Set<Factura> setAfip = new HashSet<>();
        setAfip.addAll(afip);
        System.out.println("ar.nex.factura.CompararFC.<init>()" + setAfip.size());

        int fc = 0;
        for (Factura fcSistema : sistema) {
            for (Factura fcAfip : setAfip) {
                if (fcSistema.equals(fcAfip)) {
                    System.out.println("ar.nex.factura.CompararFC.<init>()>>>>>>>>>>>>>>>>>" + fc++);
                }
            }

        }
        System.out.println("ar.nex.factura.CompararFC.<init>()" + setAfip.size());
    }

}
