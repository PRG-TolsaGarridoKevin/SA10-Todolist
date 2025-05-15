package es.progcipfpbatoi.todolistspring.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Tarea {

    private int codigo;
    private String nombre;
    private String descripcion;
    private LocalDateTime creadoEn;
    private TipusPrioritat prioritat;
    private boolean realizada;
    private LocalDate fechaVencimiento;
    private LocalTime horaVienciment;
    

    public Tarea(int codigo, String nombre, String descripcion, TipusPrioritat prioritat, boolean realizada, LocalDate fechaVencimiento, LocalTime horaVienciment) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadoEn = LocalDateTime.now();
        this.prioritat = prioritat;
        this.realizada = realizada;
        this.fechaVencimiento = fechaVencimiento;
        this.horaVienciment = horaVienciment;
    }

    public int getCodigo() {
        return codigo;
    }

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}

	public TipusPrioritat getPrioritat() {
		return prioritat;
	}
	
	public boolean getIsRealizada() {
		return realizada;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public LocalTime getHoraVienciment() {
		return horaVienciment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, creadoEn, descripcion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return codigo == other.codigo;
	}
    
    
}
