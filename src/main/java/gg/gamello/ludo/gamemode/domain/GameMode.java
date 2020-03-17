package gg.gamello.ludo.gamemode.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.Data;

import java.util.List;

/**
 *	Domain Aggregate Root
 */
@Data
@DynamoDBTable(tableName = "ludo-gamemode")
public class GameMode {

	public enum State {
		ACTIVE, INACTIVE
	}

	@DynamoDBHashKey(attributeName = "gmName")
	private String name;

	@DynamoDBTypeConvertedEnum
	@DynamoDBIndexHashKey(attributeName = "gmState", globalSecondaryIndexName = "idx_state")
	private State state;

	private List<Slot> slots;

	private int squares;

	private int pawns;
}
