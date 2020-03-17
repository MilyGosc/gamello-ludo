package gg.gamello.ludo.infrastructure.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import gg.gamello.ludo.infrastructure.properties.AwsProperties;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = {
		"gg.gamello.ludo.gamemode.domain"
})
public class DynamoDbConfiguration {

	private AwsProperties awsProperties;

	private AWSCredentialsProvider awsCredentialsProvider;

	public DynamoDbConfiguration(AwsProperties awsProperties, AWSCredentialsProvider awsCredentialsProvider) {
		this.awsProperties = awsProperties;
		this.awsCredentialsProvider = awsCredentialsProvider;
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder
				.standard()
				.withCredentials(awsCredentialsProvider)
				.withRegion(awsProperties.getRegion("dynamodb"))
				.build();
	}

	@Bean
	public DynamoDB dynamoDB() {
		return new DynamoDB(amazonDynamoDB());
	}

	@Bean
	@Primary
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
		return new DynamoDBMapper(amazonDynamoDB, DynamoDBMapperConfig.DEFAULT);
	}
}
