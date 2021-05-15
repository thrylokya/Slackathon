package com.slackathon.whatifollow1.models;

import com.slack.api.model.ErrorResponseMetadata;
import com.slack.api.model.view.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViewPublishResponseJson {

	private boolean ok;
    private String warning;
    private String error;
    private String needed;
    private String provided;

    private View view;

    private ErrorResponseMetadata responseMetadata;
}
