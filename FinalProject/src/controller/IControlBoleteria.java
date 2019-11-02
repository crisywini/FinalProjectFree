package controller;

import java.util.ArrayList;

import model.EstadoCivil;
import model.EstratoSocioeconomico;
import model.Genero;
import model.NivelDeEstudio;

public interface IControlBoleteria extends IControlAdministrador, IControlCliente, IControlEspectaculo {
	public ArrayList<Genero> getMisGeneros();

	public ArrayList<EstadoCivil> getEstadosCiviles();

	public ArrayList<EstratoSocioeconomico> getEstratos();

	public ArrayList<NivelDeEstudio> getMisEstudios();
}
