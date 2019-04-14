package org.openklips.server.dto

interface DtoAssembler<T, R> {

    fun assemble(source: T): R
}
