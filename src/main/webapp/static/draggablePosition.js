PrimeFaces.widget.Droppable.prototype.bindDropListener = function() {
    const _self = this;

    this.cfg.drop = function(event, ui) {
        if (_self.cfg.onDrop) {
            _self.cfg.onDrop.call(_self, event, ui);
        }
        if (_self.cfg.behaviors) {
            const dropBehavior = _self.cfg.behaviors['drop'];

            if (dropBehavior) {
                const ext = {
                    params: [
                        {name: _self.id + '_dragId', value: ui.draggable.attr('id')},
                        {name: _self.id + '_dropId', value: _self.cfg.target},
                        {name: ui.draggable.attr('id') + '_left', value: ui.position.left},
                        {name: ui.draggable.attr('id') + '_top', value: ui.position.top}
                    ]
                };

                dropBehavior.call(_self, ext);
            }
        }
    };
}