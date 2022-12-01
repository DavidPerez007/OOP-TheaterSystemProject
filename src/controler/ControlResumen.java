package controler;


import java.awt.event.ActionEvent;
import model.Ticket;
import view.VistaResumen;
import java.awt.event.ActionListener;

public class ControlResumen implements ActionListener{
    private VistaResumen vistaResumen;
    private Ticket modeloTicket;

    public ControlResumen(Ticket modeloTicket, VistaResumen vistaResumen) {
        this.vistaResumen = vistaResumen;
        this.modeloTicket = modeloTicket;
        this.vistaResumen.getRegresarBtn1().addActionListener(this);
        this.vistaResumen.getConfirmarBtn().addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent evento){
        if(this.vistaResumen.getConfirmarBtn() == evento.getSource()){
            
        }
    }
    
}
