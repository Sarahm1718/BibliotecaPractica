package com.co.sofka.Biblioteca.mapper;

import com.co.sofka.Biblioteca.dto.LoanDTO;
import com.co.sofka.Biblioteca.model.Loan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrestamoMapper {
    public Loan fromDTO(LoanDTO dto){
        Loan prestamo = new Loan();
        prestamo.setIdLoan(dto.getIdLoan());
        prestamo.setLoanData(dto.getLoanData());
        prestamo.setUserId(dto.getUserId());
        prestamo.setIdResource(dto.getIdResource());
        return prestamo;
    }
    public LoanDTO fromCollection(Loan collection){
        LoanDTO prestamoDTO = new LoanDTO();
        prestamoDTO.setIdLoan(collection.getIdLoan());
        prestamoDTO.setLoanData(collection.getLoanData());
        prestamoDTO.setUserId(collection.getUserId());
        prestamoDTO.setIdResource(collection.getIdResource());
        return prestamoDTO;
    }

    public List<LoanDTO> fromCollectionList(List<Loan> collection) {
        if (collection == null) {
            return null;

        }
        List<LoanDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Loan prestamo = (Loan) listTracks.next();
            list.add(fromCollection(prestamo));
        }

        return list;
    }
}
