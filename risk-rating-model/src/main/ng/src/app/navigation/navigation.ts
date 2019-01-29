import { FuseNavigation } from '@fuse/types';

export const navigation: FuseNavigation[] = [
    {
        id       : 'applications',
        title    : 'Applications',
        translate: 'NAV.APPLICATIONS',
        type     : 'group',
        children : [
            {
                id       : 'projects',
                title    : 'Projects',
                translate: 'NAV.PROJECTS',
                type     : 'item',
                icon     : 'email',
                url      : '/projects'
                /* badge    : {
                    title    : '25',
                    translate: 'NAV.SAMPLE.BADGE',
                    bg       : '#F44336',
                    fg       : '#FFFFFF'
                } */
            }
        ]
    }
];
