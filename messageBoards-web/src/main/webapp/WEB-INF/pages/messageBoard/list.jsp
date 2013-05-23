<%@ include file="/common/taglibs.jsp"%>
<div>

    <a href="#createMessage" role="button" class="btn btn-primary" data-toggle="modal">
        <fmt:message key="modal.createMessage" />
    </a>
    <div class="box">
        <div class="box-content nopadding">
            <div id="datatable_wrapper" class="dataTables_wrapper standalone" role="grid">
                <display:table list="${messageList.getMessageList()}"
                               pagesize="10"
                               class="table table-bordered table-hover" requestURI=""
                               id="datatable" export="true" sort="external" defaultsort="1">
                    <display:column titleKey="table.title"
                                    property="title"/>
                    <display:column titleKey="table.sender"
                                    property="sender"/>
                    <display:column property="content"
                                    titleKey="table.content" />
                    <display:column property="url"
                                    titleKey="table.url" />

                    <display:setProperty name="paging.banner.item_name">
                        <fmt:message key="messages" />
                    </display:setProperty>
                    <display:setProperty name="paging.banner.items_name">
                        <fmt:message key="messages" />
                    </display:setProperty>

                    <display:setProperty name="export.excel.filename"
                                         value="Messages.xls" />
                    <display:setProperty name="export.pdf.filename" value="Messages.pdf" />
                    <display:setProperty name="export.csv.filename" value="Messages.csv" />
                    <display:setProperty name="export.xml.filename" value="Messages.xml" />
                </display:table>
            </div>
        </div>
    </div>
</div>


<div id="messageDetail" class="modal hide fade" tabindex="-1"
     role="dialog" aria-labelledby="messageDetail" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">
            <i class="icon-remove"></i>
        </button>
        <h3 id="messageDetail_ModalLabel">
            <fmt:message key="modal.messageDetail" />
        </h3>
    </div>
    <!-- Modal popup for add Address -->
    <div class="modal-body">
        <div class="well form-horizontal">
            <div class="control-group">
                <label class="control-label" for="detail_title">
                    <fmt:message key="table.title" /></label>

                <div class="controls">
                    <input type="text" id="detail_title" class="input-large"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail_sender">
                    <fmt:message key="table.sender" /></label>

                <div class="controls">
                    <input type="text" id="detail_sender" class="input-large"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail_url">
                    <fmt:message key="table.url" /></label>

                <div class="controls">
                    <input type="text" id="detail_url" class="input-large"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="detail_content">
                    <fmt:message key="table.content" /></label>

                <div class="controls">
                    <textarea rows="10" cols="5" id="detail_content" readonly="true"></textarea>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">
            <fmt:message key="close" />
        </button>
    </div>
</div>


<div id="createMessage" class="modal hide fade" tabindex="-1"
     role="dialog" aria-labelledby="createMessage" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                aria-hidden="true">
            <i class="icon-remove"></i>
        </button>
        <h3 id="createMessageModalLabel">
            <fmt:message key="modal.createMessage" />
        </h3>
    </div>
    <!-- Modal popup for add Address -->
    <div class="modal-body">
        <form id="createMessageForm" action="#">
            <div class="well form-horizontal">
                <div class="control-group">
                    <label class="control-label" for="create_title">
                        <fmt:message key="table.title" />
                        <span class="red">*</span>
                    </label>

                    <div class="controls">
                        <input type="text" id="create_title" name="title"
                               class="input-large" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="create_sender">
                        <fmt:message key="table.sender" />
                        <span class="red">*</span>
                    </label>

                    <div class="controls">
                        <input type="text" id="create_sender" name="sender"
                               class="input-large"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="create_url">
                        <fmt:message key="table.url" />
                    </label>

                    <div class="controls">
                        <input type="text" id="create_url" name="url"
                               class="input-large"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="create_content">
                        <fmt:message key="table.content" /></label>

                    <div class="controls">
                        <textarea rows="10" cols="5" id="create_content" name="content"></textarea>
                    </div>
                </div>
            </div>
        </form>
        <p class="asterix">
            <span class="red">* </span>
            <fmt:message key="required.field" />
        </p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">
            <fmt:message key="close" />
        </button>

        <c:url var="createMessageURL" value="${ctx}/messageBoard/createMessage" />
        <input type="button" class="btn btn-primary"
               onclick="doCreateMessageAjax('<c:out value="${createMessageURL}"/>')"
               value="<fmt:message key="save"/>" />
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#createMessageForm").validate({
            rules: {
                "title": {required:true, maxlength:100},
                "sender": {required:true, maxlength:100},
                "url": {url:true, maxlength:100},
                "content": {maxlength:300}
            }
        });
    });
</script>