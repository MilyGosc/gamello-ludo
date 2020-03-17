package gg.gamello.ludo.core.application.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class GameMessage {
	private String subject;
	private Action action;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, Object> data;

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String subject;
		private Action action;
		private Map<String, Object> data;

		public Builder subject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder action(Action action) {
			this.action = action;
			return this;
		}

		public Builder addData(String key, Object value) {
			if (data == null)
				data = new HashMap<>();
			data.put(key, value);
			return this;
		}

		public GameMessage build() {
			if (subject.isEmpty())
				throw new IllegalStateException("subject can not be empty");

			if (action == null)
				throw new IllegalStateException("action can not be null");

			GameMessage message = new GameMessage();
			message.subject = this.subject;
			message.action = this.action;
			message.data = this.data;
			return message;
		}
	}
}
