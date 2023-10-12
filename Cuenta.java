package Actividad_4;

public class Cuenta {
	protected float saldo; 
	protected int numeroConsignaciones=0; 
	protected int numeroRetiros=0; 
	protected float tasaAnual; 
	protected float comisionMensual=0; 
	
	public Cuenta(float saldo, float tasaAnual) {
		this.saldo=saldo; 
		this.tasaAnual=tasaAnual; 
	}
	public void consignar(float cantidad) {
		saldo=saldo+cantidad; 
		numeroConsignaciones=numeroConsignaciones+1;
	}
	
	public void retirar(float cantidad) {
		float nuevoSaldo=saldo-cantidad; 
		if (nuevoSaldo>=0) {
			saldo-=cantidad;
			numeroRetiros=numeroRetiros+1;
		}
		else {
			System.out.println("La cantidad a retirar excede el saldo actual");		
		}
	}
	
	public void calcularInteres() {
		float tasaMensual=tasaAnual/12;
		float interesMensual=saldo*tasaMensual;
		saldo+=interesMensual;
	}
	
	public void extractoMensual() {
		saldo-=comisionMensual; 
		calcularInteres();
		
	}
}
	 class CuentaAhorros extends Cuenta {
		
		private boolean activa;
		
		public CuentaAhorros(float saldo, float tasa) {
		super(saldo, tasa);
		if (saldo < 10000) 
		activa = false;
		else
		activa = true;
		}
		
		public void retirar(float cantidad) {
		if (activa) 
		super.retirar(cantidad); 
		}
		
		public void consignar(float cantidad) {
		if (activa) {
			super.consignar(cantidad);
		}

		}
		public void extractoMensual() {
			
			if (numeroRetiros > 4) {
			comisionMensual += (numeroRetiros - 4) * 1000;
			}
			super.extractoMensual(); 
			
			if ( saldo < 10000 ) {
			activa = false;
			}
		}
		
		public void imprimir() {
			System.out.println("Saldo = $ " + saldo);
			System.out.println("Comisión mensual = $ " + comisionMensual);
			System.out.println("Número de transacciones ="  + (numeroConsignaciones + numeroRetiros));
			System.out.println();
			}
	}

		
	 class CuentaCorriente extends Cuenta {
	
		float sobregiro;
		
		public CuentaCorriente(float saldo, float tasa) {
		super(saldo, tasa);
		sobregiro = 0; 
		}
		
		public void retirar(float cantidad) {
		float resultado = saldo - cantidad; 
			if (resultado < 0) {
				sobregiro = sobregiro - resultado;
				saldo = 0;
			} 
			else {
				super.retirar(cantidad); 
			}
		}
		
		public void consignar(float cantidad) {
		float residuo = sobregiro - cantidad;

		if (sobregiro > 0) {
			if ( residuo > 0) {
				sobregiro = 0;
				saldo = residuo;
			}
			else { 
				sobregiro = -residuo;
				saldo = 0;
			}
		} 
		else {
			super.consignar(cantidad); 
		}
		}
			
		public void extractoMensual() {
			super.extractoMensual(); 
		}
			
		public void imprimir() {
			System.out.println("Saldo = $ " + saldo);
			System.out.println("Cargo mensual = $ " + comisionMensual);
			System.out.println("Número de transacciones =" + 
			(numeroConsignaciones + numeroRetiros));
			System.out.println("Valor de sogregiro = $" + 
			(numeroConsignaciones + numeroRetiros));
			System.out.println();
		}
	}

	
		
	
	
	


