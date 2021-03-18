package com.javainuse.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//Que se encargue de realizar la configuración de la aplicación sin usar el fichero o archivo
              // :Anotación encargada de definir que la clase
public class RabbitMQConfig {

	@Value("${javainuse.rabbitmq.queue}")//inicializar las propiedades.
	String queueName;

	@Value("${javainuse.rabbitmq.exchange}")
	String exchange;//Clase sincronizadora. Define un punto de encuentro en el que dos threads se encuentran e intercambian un objeto entre ellos.

	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;

	@Bean//Anotación que marca como bean cada uno de los métodos de tal forma que esten disponibles para Spring
	/**
	 * Queve Una cola es una estructura de datos, caracterizada por ser una secuencia de elementos en la que la operación de
	 * inserción push se realiza por un extremo y la operación de extracción pull por el otro.
	 * */
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	/**
	 * transmite todos los mensajes a todos los consumidores. Queremos ampliar eso para permitir el
	 * filtrado de mensajes según su gravedad.
	 * */
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	/**
	 * El binding es la relación que se establece entre la invocación a un procedimiento-función-método y el
	 * código que se ejecuta. Otro nombre que le damos es dispatch. Los conceptos de binding, polimorfismo y
	 * sobrecarga son aplicables a muchos paradigmas, y tienen diferentes formas de ser entendidos en cada uno
	 * de ellos.
	 * */
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	@Bean
	/**
	 * Conversor de mensajes de interfaz
	 * */
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();//Convertidor JSON que usa la biblioteca Jackson 2 Json.
	}

	
	@Bean
	/**
	 * Descripción: Realiza una factoria de conexión a base de datos
	 * */
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory); //RESTTemplate es la clase que ofrece Spring para el acceso desde la parte cliente a Servicios REST.
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
