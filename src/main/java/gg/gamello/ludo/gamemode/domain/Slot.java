package gg.gamello.ludo.gamemode.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;

import java.io.Serializable;

@Data
@DynamoDBDocument
public class Slot implements Serializable {

	private int start;

	private int finish;
}
