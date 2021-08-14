create table accounting_point_key_room
(
    id                  bigint generated by default as identity,
    version             bigint,
    accounting_point_id bigint not null,
    key_room_id         bigint not null,
    primary key (id),
    foreign key (accounting_point_id)
        references accounting_points,
    foreign key (key_room_id)
        references key_rooms,
);

create table accounting_point_key_room_services
(
    id                           bigint generated by default as identity,
    version                      bigint,
    accounting_point_key_room_id bigint not null,
    direction_of_use_id          bigint not null,
    service_id                   bigint not null,
    primary key (id),
    foreign key (accounting_point_key_room_id)
        references accounting_point_key_room,
    foreign key (direction_of_use_id)
        references direction_of_uses,
    foreign key (service_id)
        references services
);

create table accounting_point_meter_states
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    period                               timestamp not null,
    accounting_point_key_room_service_id bigint    not null,
    meter_id                             bigint    not null,
    meter_state_id                       bigint    not null,
    primary key (id),
    foreign key (accounting_point_key_room_service_id)
        references accounting_point_key_room_services,
    foreign key (meter_id)
        references meters
);

create table accounting_point_service_avg_volume
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    avg_volume                           integer not null,
    calculation_period                   date    not null,
    accounting_point_key_room_service_id bigint  not null,
    service_part_id                      bigint,
    primary key (id),
    foreign key (accounting_point_key_room_service_id)
        references accounting_point_key_room_services,
    foreign key (service_part_id)
        references services
);

create table accounting_point_service_provider
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    period                               timestamp not null,
    accounting_point_key_room_service_id bigint    not null,
    provider_id                          bigint    not null,
    service_part_id                      bigint,
    primary key (id),

    foreign key (accounting_point_key_room_service_id)
        references accounting_point_key_room_services,
    foreign key (provider_id)
        references providers,
    foreign key (service_part_id)
        references services
);

create table accounting_point_service_state
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    period                               timestamp not null,
    active                               boolean   not null,
    accounting_point_key_room_service_id bigint    not null,
    primary key (id),
    foreign key (accounting_point_key_room_service_id)
        references accounting_point_key_room_services
);

create table accounting_points
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table buildings
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table calculation_method_direction_of_use
(
    id                   bigint generated by default as identity,
    version              bigint,
    period               timestamp not null,
    calculate_full_month boolean   not null,
    norm_by_day          boolean   not null,
    direction_of_use_id  bigint    not null,
    service_id           bigint    not null,
    square_type_id       integer    not null,
    primary key (id),
    foreign key (direction_of_use_id)
        references direction_of_uses,
    foreign key (service_id)
        references services
);

create table customers
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table differentiation_types
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table direction_of_uses
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table improvement_types
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table key_norms
(
    id      bigint generated by default as identity,
    version bigint,
    primary key (id)
);

create table key_rooms
(
    id             bigint generated by default as identity,
    version        bigint,
    private_sector boolean not null,
    building_id    bigint  not null,
    room_id        bigint,
    primary key (id),
    foreign key (building_id)
        references buildings,
    foreign key (room_id)
        references rooms
);

create table meter_differentiation_type
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    period                               timestamp not null,
    accounting_point_key_room_service_id bigint    not null,
    differentiation_type_id              bigint    not null,
    meter_id                             bigint    not null,
    primary key (id),
    foreign key (accounting_point_key_room_service_id)
        references accounting_point_key_room_services,
    foreign key (differentiation_type_id)
        references differentiation_types,
    foreign key (meter_id)
        references meters
);

create table meter_types
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table meter_values
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    period                               timestamp not null,
    value                                bigint    not null,
    accounting_point_key_room_service_id bigint    not null,
    meter_id                             bigint    not null,
    primary key (id),
    foreign key (accounting_point_key_room_service_id)
        references accounting_point_key_room_services,
    foreign key (meter_id)
        references meters
);

create table meters
(
    id            bigint generated by default as identity,
    version       bigint,
    description   varchar(50) not null,
    meter_type_id bigint      not null,
    primary key (id),
    foreign key (meter_type_id)
        references meter_types
);

create table period_seasonality
(
    id             bigint generated by default as identity,
    version        bigint,
    period         timestamp not null,
    day_end        integer   not null,
    day_start      integer   not null,
    month_end      integer   not null,
    month_start    integer   not null,
    year           integer   not null,
    seasonality_id bigint    not null,
    primary key (id),
    foreign key (seasonality_id)
        references seasonality
);

create table providers
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table rate_groups
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table rate_values
(
    id            bigint generated by default as identity,
    version       bigint,
    period        timestamp not null,
    norm_value    integer   not null,
    rate_value    integer   not null,
    key_norm_id   bigint    not null,
    rate_group_id bigint    not null,
    service_id    bigint    not null,
    primary key (id),
    foreign key (key_norm_id)
        references key_norms,
    foreign key (rate_group_id)
        references rate_groups,
    foreign key (service_id)
        references services
);

create table room_improvement_types
(
    id                  bigint generated by default as identity,
    version             bigint,
    period              timestamp not null,
    improvement_type_id bigint    not null,
    key_room_id         bigint    not null,
    service_id          bigint    not null,
    primary key (id),
    foreign key (improvement_type_id)
        references improvement_types,
    foreign key (key_room_id)
        references key_rooms,
    foreign key (service_id)
        references services
);

create table room_owners
(
    id          bigint generated by default as identity,
    version     bigint,
    period      timestamp not null,
    owner_count integer   not null,
    key_room_id bigint    not null,
    primary key (id),
    foreign key (key_room_id)
        references key_rooms
);

create table room_prescribers
(
    id               bigint generated by default as identity,
    version          bigint,
    period           timestamp not null,
    prescribed_count integer   not null,
    key_room_id      bigint    not null,
    primary key (id),
    foreign key (key_room_id)
        references key_rooms
);

create table room_rate_groups
(
    id            bigint generated by default as identity,
    version       bigint,
    period        timestamp not null,
    key_room_id   bigint    not null,
    rate_group_id bigint    not null,
    service_id    bigint    not null,
    primary key (id),
    foreign key (rate_group_id)
        references rate_groups,
    foreign key (service_id)
        references services,
    foreign key (key_room_id)
        references key_rooms
);

create table room_residents
(
    id             bigint generated by default as identity,
    version        bigint,
    period         timestamp not null,
    resident_count integer   not null,
    key_room_id    bigint    not null,
    primary key (id),
    foreign key (key_room_id)
        references key_rooms

);

create table room_service_key_norms
(
    id          bigint generated by default as identity,
    version     bigint,
    period      timestamp not null,
    key_norm_id bigint    not null,
    key_room_id bigint    not null,
    service_id  bigint    not null,
    primary key (id),
    foreign key (key_norm_id)
        references key_norms,
    foreign key (key_room_id)
        references key_rooms,
    foreign key (service_id)
        references services

);

create table room_squares
(
    id             bigint generated by default as identity,
    version        bigint,
    period         timestamp not null,
    value          integer   not null,
    key_room_id    bigint    not null,
    square_type_id integer    not null,
    primary key (id),
    foreign key (key_room_id)
        references key_rooms
);

create table rooms
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    building_id bigint      not null,
    parent_id   bigint,
    primary key (id),
    foreign key (building_id)
        references buildings,
    foreign key (parent_id)
        references rooms
);

create table seasonality
(
    id          bigint generated by default as identity,
    version     bigint,
    description varchar(50) not null,
    primary key (id)
);

create table seasonality_settings
(
    id                                            bigint generated by default as identity,
    version                                       bigint,
    period                                        timestamp not null,
    coefficient_norm_value                        integer   not null,
    coefficient_norm_value_do_not_use_seasonality integer   not null,
    correct_annual_volume                         boolean   not null,
    do_not_use_seasonality                        boolean   not null,
    volume_by_last_year                           boolean   not null,
    building_id                                   bigint,
    direction_of_use_id                           bigint    not null,
    service_id                                    bigint    not null,
    primary key (id),
    foreign key (building_id)
        references buildings,
    foreign key (direction_of_use_id)
        references direction_of_uses,
    foreign key (service_id)
        references services

);

create table service_volume_values
(
    id                    bigint generated by default as identity,
    version               bigint,
    volume                bigint not null,
    calculation_method_id bigint not null,
    meter_value_end_id    bigint,
    meter_value_start_id  bigint,
    stab_period_id        bigint not null,
    primary key (id),
    foreign key (meter_value_end_id)
        references meter_values,
    foreign key (meter_value_start_id)
        references meter_values,
    foreign key (stab_period_id)
        references stability_periods
);

create table services
(
    id                   bigint generated by default as identity,
    version              bigint,
    description          varchar(50) not null,
    depend_on_service_id bigint,
    primary key (id),
    foreign key (depend_on_service_id)
        references services
);

create table stability_periods
(
    id                                   bigint generated by default as identity,
    version                              bigint,
    calculation_period                   date   not null,
    registration_period                  date   not null,
    registration_period_fact             date   not null,
    accounting_point_id                  bigint,
    accounting_point_meter_state_id      bigint,
    accounting_point_service_provider_id bigint,
    accounting_point_service_state_id    bigint not null,
    meter_differentiation_type_id        bigint,
    next_row_id                          bigint,
    room_common_square_id                bigint,
    room_owner_id                        bigint,
    room_prescribed_id                   bigint,
    room_rate_group_id                   bigint,
    room_resident_id                     bigint,
    room_service_key_norm_id             bigint,
    room_square_id                       bigint,
    primary key (id),

    foreign key (accounting_point_id)
        references accounting_point_key_room_services,
    foreign key (accounting_point_meter_state_id)
        references accounting_point_meter_states,
    foreign key (accounting_point_service_provider_id)
        references accounting_point_service_provider,
    foreign key (accounting_point_service_state_id)
        references accounting_point_service_state,
    foreign key (meter_differentiation_type_id)
        references meter_differentiation_type,
    foreign key (next_row_id)
        references stability_periods,
    foreign key (room_common_square_id)
        references room_squares,
    foreign key (room_owner_id)
        references room_owners,
    foreign key (room_prescribed_id)
        references room_prescribers,
    foreign key (room_rate_group_id)
        references room_rate_groups,
    foreign key (room_resident_id)
        references room_residents,
    foreign key (room_service_key_norm_id)
        references room_service_key_norms,
    foreign key (room_square_id)
        references room_squares
);





