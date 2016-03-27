<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="panel panel-default panel-table">
						<div class="panel-heading">
							<div class="row">
								<div class="col col-xs-6">
									<h3 class="panel-title">Panel Heading</h3>
								</div>
								<div class="col col-xs-6 text-right">
									<button type="button" class="btn btn-sm btn-primary btn-create">Create
										New</button>
								</div>
							</div>
						</div>
						<div class="panel-body">
							<table class="table table-striped table-bordered table-list">
								<thead>
									<tr>
										<th><em class="fa fa-cog"></em></th>
										<th class="hidden-xs">ID</th>
										<th>Name</th>
										<th>Email</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"><a class="btn btn-default"><em
												class="fa fa-pencil"></em></a> <a class="btn btn-danger"><em
												class="fa fa-trash"></em></a></td>
										<td class="hidden-xs">1</td>
										<td>John Doe</td>
										<td>johndoe@example.com</td>
									</tr>
								</tbody>
							</table>

						</div>
						<div class="panel-footer">
							<div class="row" ng-show="accounts.length">
								<uib-pagination class="pull-right" total-items="totalItem"
									ng-model="curPage" max-size="numPages" class="pagination-md"
									items-per-page="pageSize" boundary-links="true"
									style="margin-right: 20px;" ng-change="pageChanged()"></uib-pagination>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>