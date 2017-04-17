package org.debugroom.sample.cassandra.app.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GroupResource<G> {

	private G group;

}
