package com.slackathon.whatifollow1.models;

import com.slack.api.model.Conversation;

public class ConversationsInfoResponse {
	 private boolean ok;
	    private String warning;
	    private String error;
	    private String needed;
	    private String provided;

	    private Conversation channel;

		public boolean isOk() {
			return ok;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

		public String getWarning() {
			return warning;
		}

		public void setWarning(String warning) {
			this.warning = warning;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getNeeded() {
			return needed;
		}

		public void setNeeded(String needed) {
			this.needed = needed;
		}

		public String getProvided() {
			return provided;
		}

		public void setProvided(String provided) {
			this.provided = provided;
		}

		public Conversation getChannel() {
			return channel;
		}

		public void setChannel(Conversation channel) {
			this.channel = channel;
		}
}
