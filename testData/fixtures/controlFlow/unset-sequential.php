<?php

function cases_holder() {
    unset($x);
    <weak_warning descr="Can be replaced with 'unset(..., ...)' construction (simplification).">unset($y);</weak_warning>
    /** dock-block should not break inspection */
    /** multiple dock-blocks should not break inspection */
    <weak_warning descr="Can be replaced with 'unset(..., ...)' construction (simplification).">unset ($z);</weak_warning>

    $x = $y = $z;
    unset($x, $y);
    <weak_warning descr="Can be replaced with 'unset(..., ...)' construction (simplification).">unset($z);</weak_warning>
}