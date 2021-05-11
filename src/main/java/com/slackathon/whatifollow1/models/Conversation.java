package com.slackathon.whatifollow1.models;

import java.util.List;

import javax.xml.crypto.KeySelector.Purpose;

import com.google.gson.annotations.SerializedName;
import com.slack.api.model.Latest;
import com.slack.api.model.Topic;

public class Conversation {

    private String enterpriseId;
    public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getUnlinked() {
		return unlinked;
	}
	public void setUnlinked(Integer unlinked) {
		this.unlinked = unlinked;
	}
	public String getNameNormalized() {
		return nameNormalized;
	}
	public void setNameNormalized(String nameNormalized) {
		this.nameNormalized = nameNormalized;
	}
	public List<String> getPendingShared() {
		return pendingShared;
	}
	public void setPendingShared(List<String> pendingShared) {
		this.pendingShared = pendingShared;
	}
	public String getLastRead() {
		return lastRead;
	}
	public void setLastRead(String lastRead) {
		this.lastRead = lastRead;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Purpose getPurpose() {
		return purpose;
	}
	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}
	public List<String> getPreviousNames() {
		return previousNames;
	}
	public void setPreviousNames(List<String> previousNames) {
		this.previousNames = previousNames;
	}
	public Integer getNumOfMembers() {
		return numOfMembers;
	}
	public void setNumOfMembers(Integer numOfMembers) {
		this.numOfMembers = numOfMembers;
	}
	public Latest getLatest() {
		return latest;
	}
	public void setLatest(Latest latest) {
		this.latest = latest;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Integer getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(Integer unreadCount) {
		this.unreadCount = unreadCount;
	}
	public Integer getUnreadCountDisplay() {
		return unreadCountDisplay;
	}
	public void setUnreadCountDisplay(Integer unreadCountDisplay) {
		this.unreadCountDisplay = unreadCountDisplay;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Double getPriority() {
		return priority;
	}
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	public Integer getDateConnected() {
		return dateConnected;
	}
	public void setDateConnected(Integer dateConnected) {
		this.dateConnected = dateConnected;
	}
	public List<String> getSharedTeamIds() {
		return sharedTeamIds;
	}
	public void setSharedTeamIds(List<String> sharedTeamIds) {
		this.sharedTeamIds = sharedTeamIds;
	}
	public String getParentConversation() {
		return parentConversation;
	}
	public void setParentConversation(String parentConversation) {
		this.parentConversation = parentConversation;
	}
	public List<String> getPendingConnectedTeamIds() {
		return pendingConnectedTeamIds;
	}
	public void setPendingConnectedTeamIds(List<String> pendingConnectedTeamIds) {
		this.pendingConnectedTeamIds = pendingConnectedTeamIds;
	}
	public String getConversationHostId() {
		return conversationHostId;
	}
	public void setConversationHostId(String conversationHostId) {
		this.conversationHostId = conversationHostId;
	}
	public List<String> getInternalTeamIds() {
		return internalTeamIds;
	}
	public void setInternalTeamIds(List<String> internalTeamIds) {
		this.internalTeamIds = internalTeamIds;
	}
	public List<String> getConnectedTeamIds() {
		return connectedTeamIds;
	}
	public void setConnectedTeamIds(List<String> connectedTeamIds) {
		this.connectedTeamIds = connectedTeamIds;
	}
	public boolean isChannel() {
		return isChannel;
	}
	public void setChannel(boolean isChannel) {
		this.isChannel = isChannel;
	}
	public boolean isGroup() {
		return isGroup;
	}
	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}
	public boolean isIm() {
		return isIm;
	}
	public void setIm(boolean isIm) {
		this.isIm = isIm;
	}
	public boolean isArchived() {
		return isArchived;
	}
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
	public boolean isGeneral() {
		return isGeneral;
	}
	public void setGeneral(boolean isGeneral) {
		this.isGeneral = isGeneral;
	}
	public boolean isReadOnly() {
		return isReadOnly;
	}
	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public boolean isThreadOnly() {
		return isThreadOnly;
	}
	public void setThreadOnly(boolean isThreadOnly) {
		this.isThreadOnly = isThreadOnly;
	}
	public boolean isNonThreadable() {
		return isNonThreadable;
	}
	public void setNonThreadable(boolean isNonThreadable) {
		this.isNonThreadable = isNonThreadable;
	}
	public boolean isShared() {
		return isShared;
	}
	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}
	public boolean isExtShared() {
		return isExtShared;
	}
	public void setExtShared(boolean isExtShared) {
		this.isExtShared = isExtShared;
	}
	public boolean isOrgShared() {
		return isOrgShared;
	}
	public void setOrgShared(boolean isOrgShared) {
		this.isOrgShared = isOrgShared;
	}
	public boolean isPendingExtShared() {
		return isPendingExtShared;
	}
	public void setPendingExtShared(boolean isPendingExtShared) {
		this.isPendingExtShared = isPendingExtShared;
	}
	public boolean isGlobalShared() {
		return globalShared;
	}
	public void setGlobalShared(boolean globalShared) {
		this.globalShared = globalShared;
	}
	public boolean isOrgDefault() {
		return orgDefault;
	}
	public void setOrgDefault(boolean orgDefault) {
		this.orgDefault = orgDefault;
	}
	public boolean isOrgMandatory() {
		return orgMandatory;
	}
	public void setOrgMandatory(boolean orgMandatory) {
		this.orgMandatory = orgMandatory;
	}
	public Integer getIsMoved() {
		return isMoved;
	}
	public void setIsMoved(Integer isMoved) {
		this.isMoved = isMoved;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public boolean isMpim() {
		return isMpim;
	}
	public void setMpim(boolean isMpim) {
		this.isMpim = isMpim;
	}
	private String id;
    private String name;
    private String created;
    private String creator;
    private Integer unlinked;
    @SerializedName("name_normalized")
    private String nameNormalized;
    @SerializedName("pending_shared")
    private List<String> pendingShared;
    @SerializedName("last_read")
    private String lastRead;
    private Topic topic;
    private Purpose purpose;
    @SerializedName("previous_names")
    private List<String> previousNames;
    @SerializedName("num_members")
    private Integer numOfMembers;
    private Latest latest;
    private String locale;
    @SerializedName("unread_count")
    private Integer unreadCount;
    @SerializedName("unread_count_display")
    private Integer unreadCountDisplay;
    private String user; // conversations.open
    private Double priority;

    private Integer dateConnected;

    private List<String> sharedTeamIds;

    private String parentConversation;
    private List<String> pendingConnectedTeamIds;

    // shared channels
    private String conversationHostId;
    private List<String> internalTeamIds;
    private List<String> connectedTeamIds;

    @SerializedName("is_channel")
    private boolean isChannel;
    @SerializedName("is_group")
    private boolean isGroup;
    @SerializedName("is_im")
    private boolean isIm;
    @SerializedName("is_archived")
    private boolean isArchived;
    @SerializedName("is_general")
    private boolean isGeneral;
    @SerializedName("is_read_only")
    private boolean isReadOnly;
    @SerializedName("is_thread_only")
    private boolean isThreadOnly;
    @SerializedName("is_non_threadable")
    private boolean isNonThreadable;
    @SerializedName("is_shared")
    private boolean isShared;
    @SerializedName("is_ext_shared")
    private boolean isExtShared;
    @SerializedName("is_org_shared")
    private boolean isOrgShared;
    @SerializedName("is_pending_ext_shared")
    private boolean isPendingExtShared;
    @SerializedName("is_global_shared")
    private boolean globalShared;
    @SerializedName("is_org_default")
    private boolean orgDefault;
    @SerializedName("is_org_mandatory")
    private boolean orgMandatory;
    @SerializedName("is_moved")
    private Integer isMoved;
    @SerializedName("is_member")
    private boolean isMember;
    @SerializedName("is_open")
    private boolean open;
    @SerializedName("is_private")
    private boolean isPrivate;
    @SerializedName("is_mpim")
    private boolean isMpim;
}